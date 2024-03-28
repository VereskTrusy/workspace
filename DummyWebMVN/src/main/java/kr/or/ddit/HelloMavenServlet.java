package kr.or.ddit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloMavenServlet extends HttpServlet{
	// 처음엔 HttpServlet 에 대한 doc 없다.
	// 필요 시 다운로드하여 보여 질 수 있다.
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
	}
}
