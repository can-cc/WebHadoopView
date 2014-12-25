package massage;

import hdfs.GetFileInfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Json;
import data.JsonArray;

import tyanX.TyanX;

/**
 * Servlet implementation class MSG
 */
public class MSG extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MSG() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("test");
		out.flush(); // 
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		TyanX ty1=new TyanX();
//		String test=ty1.createWidget();

		response.setContentType("text/javascript;charset=UTF-8");
		String path=request.getParameter("path");

		PrintWriter out = response.getWriter();
//		out.println(test);
		
		GetFileInfo test=new GetFileInfo();
		
		
		Json tmp =new Json();
		tmp.put("task","createWidget");
		JsonArray list =new JsonArray();
		list.put(tmp);
		
		list.add(test.GetFolder(path));
        Json action=new Json();
        action.put("action", list);

        
        out.println(action.toString());
		

		
		out.flush(); 
		out.close();
	}

}
