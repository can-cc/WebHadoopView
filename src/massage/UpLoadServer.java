package massage;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpLoadServer extends HttpServlet {

	/**
*
*/
	private static final long serialVersionUID = 1L;
	private ServletContext sc;
	private String savePath;

	/**
	 * Constructor of the object.
	 */
	public UpLoadServer() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List items = upload.parseRequest(request);
			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (item.isFormField()) {
					System.out.println("utf-8 " + item.getFieldName()
							+ "utf-8: " + item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("size: " + item.getSize());
						System.out.println("type: " + item.getContentType());
						System.out.println("name: " + item.getName());

						File tempFile = new File(item.getName());
						savePath="temp";
						File file = new File(sc.getRealPath("/") + savePath,
								tempFile.getName());
						item.write(file);

						request.setAttribute("upload.message", "massage");

					} else {
						request.setAttribute("upload.message", "errormassage");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "errormassage! ");
		}
		request.getRequestDispatcher("/uploadResult.jsp").forward(request,
				response);
	}

	public void init(ServletConfig config) {
		savePath = config.getInitParameter("savePath");
		sc = config.getServletContext();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
}
