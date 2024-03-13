package kr.or.ddit.login;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet {
	
	private boolean authenticate(String id, String password) { // 로그인 성공여부 판단
		return id.equals(password);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. body 영역의 디코딩에 사용할 charset 결정
		req.setCharacterEncoding("UTF-8");
		try {
			// 2. 필요 파라미터 확보
			// 3. 파라미터 검증
			// 	- 검증 통과 : 
			String memId = Optional.of(req.getParameter("memId"))
				.filter(id->!id.isEmpty())
				.orElseThrow(()->new IllegalArgumentException("아이디 누락"));
			
			String memPass = Optional.of(req.getParameter("memPass"))
					.filter(id->!id.isEmpty())
					.orElseThrow(()->new IllegalArgumentException("비밀번호 누락"));
			
			// 4. 인증 여부 판단.
			if(authenticate(memId, memPass)) { // - 성공 : 웰컴 페이지로 이동 - 리다이렉트
				resp.sendRedirect(req.getContextPath() + "/");
			}else { // - 실패 : 로그인 페이지로 이동 - 1. 리퀘살려, 2. A는 응답책임없으 -> forward	
				req.getRequestDispatcher("/login/loginForm.jsp").forward(req, resp);
			}
			
		}catch (RuntimeException e) {
			//		- 검증 실패 : 상태코드 400
			resp.sendError(400, e.getMessage());
		}
		
	}
}
