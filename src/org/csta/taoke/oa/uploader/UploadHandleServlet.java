package org.csta.taoke.oa.uploader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadHandleServlet
 * 别问我为什么 @Deprecated
 * 因为Spring MVC上传文件比这个简单多了哈哈哈哈哈
 */
@Deprecated
@WebServlet("/UploadHandleServlet")
public class UploadHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadHandleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File saveFile = new File(savePath);
//		判断上传文件的保存目录是否存在
		if (!saveFile.exists() && !saveFile.isDirectory()) {
			System.out.println("Directory "+savePath+" doesnot exist, create it.");
			saveFile.mkdir();
		}
//		消息提示
		String message = "";
		try {
//			使用Apache文件上传组件处理文件上传步骤
//			1、创建一个DiskFileItemFactory工厂类
			DiskFileItemFactory factory = new DiskFileItemFactory();
//			2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); 
//			3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
//			4、使用ServletFileUpload解析器解析上传数据
//			解析结果返回的是一个List<FileItem>集合
//			每一个FileItem对应一个Form表单的输入项
			List<FileItem> fileItemList = upload.parseRequest(request);
			for(FileItem fileItem : fileItemList) {
//				如果fileitem中封装的是普通输入项的数据
				if(fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");
					System.out.println(name + "=" + value);
//				如果fileitem中封装的是上传文件
				} else {
//					得到上传的文件名称
					String filename = fileItem.getName();
					if(filename==null || filename.trim().equals("")) {
						continue;
					}
					filename = filename.substring(filename.lastIndexOf("/")+1);
					InputStream in = fileItem.getInputStream();
					FileOutputStream out = new FileOutputStream(savePath + "/" + filename);
					byte buffer[] = new byte[1024];
					int len = 0;
					while( (len = in.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					fileItem.delete();
					message = "文件上传成功！";
				}
			}
		} catch (Exception e) {
			message= "文件上传失败！";
			e.printStackTrace();
		}
		request.setAttribute("message",message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
}
