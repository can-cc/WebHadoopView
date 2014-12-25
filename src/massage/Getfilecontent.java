package massage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.LineReader;

public class Getfilecontent extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Getfilecontent() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, FileNotFoundException{
		
		

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String filename=new String(request.getParameter("filename").getBytes("ISO-8859-1"),"UTF-8");
		
		out.println("<textarea id='"+"redactor1"+"' name='content'>");
		
		
		
		 String inputPath = "hdfs://localhost:9000/usr/local/hadoop/hdfs/home/"+filename; 
		 Configuration conf = new Configuration();
		 FileSystem hdfs = FileSystem.get(URI.create(inputPath), conf);
		 Path inPath = new Path(inputPath);
		  
		 FSDataInputStream dis = hdfs.open(inPath);
		 LineReader in = new LineReader(dis,conf);  
		 Text line = new Text();
		 //按行读取
		 while(in.readLine(line) > 0){
		      out.println((line.toString()));
		 }
		 out.println("</textarea>");
		 out.println("<script>");
		 out.println("$('#redactor1').redactor({");
		 out.println("lang: 'zh_cn',");
		 out.println("wym: true");
		 out.println("});");
		 out.println("</script>");

		 
		 
		 dis.close();
		 in.close();
		 
		 out.flush();
		 out.close();
		
		
	}

}
