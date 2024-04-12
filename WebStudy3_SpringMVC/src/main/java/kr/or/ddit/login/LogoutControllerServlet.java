package kr.or.ddit.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 현재 사용자의 세션 즉시 만료.
		HttpSession session = req.getSession();
		// 현재 세션은 이미 로그인이 되어 있기 때문에 null은 아니다.
		// 
		if(session.isNew()) {
			resp.sendError(400, "현재의 요청은 최초의 요청일 수 없음.");
		}
		
		// 세션 만료 진행
		session.invalidate();
		String message = "로그아웃 성공";
		
		// html 에서 form url을 인코딩 하기
		message = URLEncoder.encode(message, "UTF-8");
		
		// 웰컴 페이지로 이동.
		String viewName = "redirect:/?message=" + message;
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
