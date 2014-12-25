package massage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Servlet implementation class FileOperate
 */
public class FileOperate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileOperate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out = response.getWriter();
		
		  String filename=new String(request.getParameter("filename").getBytes("ISO-8859-1"),"UTF-8");
		  String operate=request.getParameter("operate");
		//  String filename=request.getParameter("filename");
		  
		  if(operate.equals("delete")){
			  String dst = "hdfs://localhost:9000/usr/local/hadoop/hdfs/home/"+filename;  
			  Configuration conf = new Configuration();  
			  FileSystem fs = FileSystem.get(URI.create(dst), conf);
			  boolean check = fs.deleteOnExit(new Path(dst));
			  fs.close();
			  out.println(check);
		  }else{
			  out.println("operate error");			  
		  }
		  out.flush();
		  out.close();

	}



}
