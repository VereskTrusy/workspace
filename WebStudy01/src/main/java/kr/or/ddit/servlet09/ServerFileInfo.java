package kr.or.ddit.servlet09;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/case2/fileInfo")
public class ServerFileInfo extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext(); // 패스 정보 구하기 위해 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 받기
		String path = req.getParameter("path");
		
		// 파라미터 검증
		if(StringUtils.isBlank(path)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		// 경로 변환 : 로지컬 패스 -> 피지컬 패스
		String realPath = application.getRealPath(path);
		
		// 파일 검증
		File file = new File(realPath);
		if(!file.exists() || file.isDirectory()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		// 파일 사이즈 구하기
		long size = file.length();
		req.setAttribute("size", size);
		
		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
