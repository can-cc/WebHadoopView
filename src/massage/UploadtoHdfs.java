package massage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
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
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class UploadtoHdfs extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadtoHdfs() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestContext requestContext = new ServletRequestContext(request);
		if (FileUpload.isMultipartContent(requestContext)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File("/usr/Log/"));
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

						String filePath = "hdfs://localhost:9000/usr/local/hadoop/tmp/hdfs/";
						File fp = new File(filePath);
						// 创建目录
						if (!fp.exists()) {
							fp.mkdirs();// 目录不存在的情况下，创建目录。
						}

						Configuration conf = new Configuration();
						String path = filePath + fullFile.getName();
						FileSystem fs = FileSystem.get(URI.create(path), conf);
						OutputStream out = fs.create(new Path(path),
								new Progressable() {
									public void progress() {
										System.out.print(".");
									}
								});

						try {
							IOUtils.copyBytes(fileItem.getInputStream(), out,
									4096, true);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("文件没有选择 或 文件内容为空");
					}
				}

			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
