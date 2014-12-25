package massage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;

import data.Json;
import data.JsonArray;
import data.SortHashtable;

/**
 * Servlet implementation class GetHbaseContent
 */
public class GetHbaseContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final byte[] POSTFIX = new byte[] { 0x00 };
	private static Configuration conf = null;
	static byte[] lastRow = null;
	static byte[] last2Row = null;
	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "localhost");
		conf.set("hbase.zookeeper.property.clientPort", "2222");
	}
	static HTable table = null;

	public GetHbaseContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String tablename = new String(request.getParameter("tablename")
				.getBytes("ISO-8859-1"), "UTF-8");

		if(table == null || !(new String(table.getTableName())).equals(tablename) ){
		table = new HTable(conf, tablename);
		lastRow = null;
		}
		
		Filter filter = new PageFilter(100);//分页过滤器 ,提取100行提取列值，提取不了的放弃
		Scan scan = new Scan();
		scan.setFilter(filter);
		if (lastRow != null) {
            byte[] startRow = Bytes.add(lastRow, POSTFIX);
			scan.setStartRow(startRow);
		}
		
		ResultScanner results = table.getScanner(scan); //结果集合
		
		ArrayList<String> rownamearray = new ArrayList<String>();//列值集合
		Hashtable<String, String> index = new Hashtable<String, String>(); //index集合
		
		for (Result result : results) {
			for (KeyValue rowKV : result.raw()) {
				String rowname = new String(rowKV.getFamily()) + "_"
						+ new String(rowKV.getQualifier());//列族_列名
				if (!rownamearray.contains(rowname)) {
					rownamearray.add(rowname);
				}
			}
		}
		Collections.sort(rownamearray);//排序
		rownamearray.add(0,new String("Row_Name"));//在最前面加入列名
		//封装json并写入hashtable
		JsonArray indexarray = new JsonArray();
		Integer indexnum = new Integer(0);
    	Iterator<String> it1 = rownamearray.iterator();
		while (it1.hasNext()){
			String indexname = it1.next().toString();
			Json indexCell = new Json();
			indexCell.put(indexname, indexnum.toString());
			indexarray.put(indexCell);
			index.put(indexname, indexnum.toString());
			indexnum++;
		}

		//分页功能
		

		Filter filter2 = new PageFilter(30);
		scan.setFilter(filter2);
		
		if (lastRow != null) {
            byte[] startRow = Bytes.add(lastRow, POSTFIX);
			scan.setStartRow(startRow);
		}
		ResultScanner scanner = table.getScanner(scan);

		
		JsonArray rowarray  = new JsonArray();
		
		for (Result result : scanner) {
			Json row = new Json();
			JsonArray cellArray = new JsonArray();

			Json fcell = new Json();
			String rowname = new String(result.getRow());
			String rownameindex = index.get("Row_Name").toString();
			fcell.put(rownameindex,rowname);
			cellArray.put(fcell);
			
			

			for (KeyValue rowKV : result.raw()) {
				//
				Json cell = new Json();
				String cellname = new String(rowKV.getFamily()) +"_"+ new String(rowKV.getQualifier());
				String cellindex = index.get(cellname).toString();
				String cellvalue = new String(rowKV.getValue());
				String newvalue = cellvalue.replaceAll("\"","");  //去除引号
				cell.put(cellindex, newvalue);
				cellArray.put(cell);
			}

			lastRow = result.getRow();  //本分页最后一个
			row.put("Row", cellArray);
			rowarray.put(row);
		}
		scanner.close();
		
		Json HbaseJosn = new Json();
		HbaseJosn.put("Index",indexarray);
		HbaseJosn.put("Table",rowarray);

		out.println(HbaseJosn);
		out.flush();
		out.close();

	}
}
