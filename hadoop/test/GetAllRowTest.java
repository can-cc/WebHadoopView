package test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;

public class GetAllRowTest {
	
	
	
	private static Configuration conf = null;
	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "localhost");
		conf.set("hbase.zookeeper.property.clientPort", "2222");
	}
	
	public static Map.Entry[] getSortedHashtableByValue(Hashtable h) {
		Set set = h.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);

		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				int key1 = Integer.parseInt(((Map.Entry) arg0).getValue()
						.toString());
				int key2 = Integer.parseInt(((Map.Entry) arg1).getValue()
						.toString());
				return ((Comparable) key1).compareTo(key2);
			}
		});

		return entries;
	}
	
	
	
	public GetAllRowTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		GetAllRowTest test = new GetAllRowTest();
		test.MakeIndex();

	}

	public void MakeIndex() throws IOException {
		String tablename = "users5";
		HTable table = new HTable(conf, tablename);
		
		Filter filter = new PageFilter(200);

		Scan scan = new Scan();
		scan.setFilter(filter);
		ResultScanner results = table.getScanner(scan);

		Hashtable<String, Integer> index = new Hashtable<String, Integer>();
		// 输出结果

		int indexnum = 0;

		for (Result result : results) {
			System.out.println(results);
			for (KeyValue rowKV : result.raw()) {
/*				System.out.print("Row Name: " + new String(rowKV.getRow())
						+ " ");
				System.out.print("Timestamp: " + rowKV.getTimestamp() + " ");
				System.out.print("column Family: "
						+ new String(rowKV.getFamily()) + " ");
				System.out.print("Row Name:  "
						+ new String(rowKV.getQualifier()) + " ");
				System.out.println("Value: " + new String(rowKV.getValue())
						+ " ");*/
				String rowname = new String(rowKV.getFamily()) + ":"
						+ new String(rowKV.getQualifier());
				if (!index.containsKey(rowname)) {
					index.put(rowname, indexnum);
					indexnum++;
				}
			}
		}
/*		Set<String> keys = index.keySet();  
        for(String key: keys){  
            System.out.println("Value of "+key+" is: "+index.get(key));  
        }  */
		 Map.Entry[] set = getSortedHashtableByValue(index);

		    // perportyTable
		    for (int i = 0; i < set.length; i++) {

		      System.out.println(set[i].getKey().toString());

		      System.out.println(set[i].getValue().toString());

		    }
	}

}
