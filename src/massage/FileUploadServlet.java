package massage;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1315419529138965268L;

	public FileUploadServlet() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("gbk");
		RequestContext requestContext = new ServletRequestContext(request);

		if (FileUpload.isMultipartContent(requestContext)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File("/home/ub/FIleupload"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setHeaderEncoding("gbk");
			upload.setSizeMax(2000000);
			List items = new ArrayList();
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				System.out.println("文件上传发生错误" + e1.getMessage());
			}

			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()
							+ " "
							+ fileItem.getName()
							+ " "
							+ new String(fileItem.getString().getBytes(
									"iso8859-1"), "gbk"));
				} else {
					System.out.println(fileItem.getFieldName() + " "
							+ fileItem.getName() + " " + fileItem.isInMemory()
							+ " " + fileItem.getContentType() + " "
							+ fileItem.getSize());

					if (fileItem.getName() != null && fileItem.getSize() != 0) {
						File fullFile = new File(fileItem.getName());
						String filePath = "/home/ub/FIleupload";  
						
				        File fp = new File(filePath);  
				        // 创建目录  
				        if (!fp.exists()) {  
				            fp.mkdirs();// 目录不存在的情况下，创建目录。  
				        }  
				        
						File newFile = new File("/home/ub/FIleupload" + fullFile.getName());
						try {
							fileItem.write(newFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("文件没有选择 或 文件内容为空");
					}
				}

			}
		}
	}

}
