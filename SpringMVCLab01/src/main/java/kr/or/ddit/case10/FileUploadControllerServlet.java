package kr.or.ddit.case10;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/case10/fileUpload1")
public class FileUploadControllerServlet extends HttpServlet{
	
	private File saveFolder = new File("F:/02.saveFolder");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/case10/fileForm1.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String upload = req.getParameter("upload");
//		String count = req.getParameter("count");
		// 일반적으로 10kbyte
		FileItemFactory itemFactory = new DiskFileItemFactory(10*1024, new File("F:/01.temp"));
		ServletFileUpload handler = new ServletFileUpload(itemFactory);
		
		try {
			List<FileItem> itemList = handler.parseRequest(req);
			
			for(FileItem part: itemList) {
				String partName = part.getFieldName();
				
				if(part.isFormField()) {
					String paramValue = part.getString(req.getCharacterEncoding());
					
					log.info("{} : {}", partName, paramValue);
				} else {
					// file
					String fileName = part.getName();
					String mime = part.getContentType();
					if(mime.startsWith("Image/")) {
						String saveName = "filename" + System.currentTimeMillis();
						long fileSize = part.getSize();
						FileUploadVO fileVO = new FileUploadVO(fileName, saveName, fileSize);
						
						File savaFile = new File(saveFolder, saveName);
						
						
						FileUtils.copyInputStreamToFile(part.getInputStream(), savaFile);
						log.info("{} 업로드 완료, 데이터베이스에 저장할 메타데이터 : {}", fileName, fileVO);
					}
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}
