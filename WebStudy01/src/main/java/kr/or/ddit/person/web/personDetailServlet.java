package kr.or.ddit.person.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.person.service.PersonService;
import kr.or.ddit.person.service.PersonServiceImpl;
import kr.or.ddit.vo.PersonVo;

@WebServlet("/people.do")
public class personDetailServlet extends HttpServlet {
	private PersonService service = new PersonServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("who");
		if(id == null || id.isEmpty()) {
			resp.sendError(resp.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		
		PersonVo person = service.retrievePerson(id);
		
		req.setAttribute("person", person);
		req.getRequestDispatcher("/WEB-INF/views/person/detail.jsp").forward(req, resp);
	}
}
// 비동기로 바꿔서 목록화면에 노출시키기 