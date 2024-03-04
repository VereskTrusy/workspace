package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageForm.do")
public class ImageForm extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일 리스트 가져오기
		File dir = new File("F:/00.medias/images");
		
		if(!dir.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("지정된 디렉터리가 없습니다."));
			return;
		}
		
		String[] fileNames = dir.list();
		List<String> list = new ArrayList<String>(Arrays.asList(fileNames));
		
		req.setAttribute("selImageList", list);
		
		req.getRequestDispatcher("/03/imageForm.jsp").forward(req, resp);
	}
}
