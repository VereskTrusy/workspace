package kr.or.ddit.servlet09;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/case2/serverFile")
public class ServerFileExplorer_case2 extends HttpServlet{
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // JsonProcessingException 얘는 앞에 애들 자식중 하나
		String accept = req.getHeader("accept");
		
		// 폴더 검색 베이스 파라메터 받기
		String base = Optional.ofNullable(req.getParameter("base"))
						.filter((bp)->!bp.isEmpty())
						.orElse("/");
		
		// 파일 or 폴더 출려 여부 파라메터 받기
		String type = Optional.ofNullable(req.getParameter("type"))
						.filter((tp)->!tp.isEmpty())
						.orElse("folder");
		
		// 파일, 폴더, 모두 보여질지 결정하는 플래그
		boolean folderFlag = "folder".equals(type);
		boolean fileFlag = "file".equals(type);
		boolean allFlag = "all".equals(type);
		
		// 파일 리스트 만들기
		List<FileWrapper> wrapperList = new ArrayList<>();
		for(String path : application.getResourcePaths(base)) {
			String realPath = application.getRealPath(path); // 파일시스템 패스
			File tmp = new File(realPath);
			if(allFlag || (folderFlag && tmp.isDirectory()) || (fileFlag && tmp.isFile())) { // 파일 디스플레이 결정 
				FileWrapper wrapper = new FileWrapper(tmp, path);
				wrapperList.add(wrapper);
			}
		}
		
		// 파일 리스트 정렬하기
		Collections.sort(wrapperList); // 정렬
		
		// 리퀘스트에 태우기
		req.setAttribute("wrapperList", wrapperList);
		
		// 이동할 View 지정
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.do";
		} else {
			// View 로 이동
			viewName = "/WEB-INF/views/explorer/fileView_case2.jsp";
		}
		
		req.getRequestDispatcher(viewName).forward(req, resp);			
	}
	
}
