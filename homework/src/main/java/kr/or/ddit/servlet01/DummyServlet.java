package kr.or.ddit.servlet01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dummyServlet.do")
public class DummyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DummyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.getWriter().append("Served at: ").append(request.getContextPath());
		// jsp로 이동 필요
//		request.getRequestDispatcher("/WEB-INF/views/inner.jsp")
//			.forward(request, response);
	}

}
