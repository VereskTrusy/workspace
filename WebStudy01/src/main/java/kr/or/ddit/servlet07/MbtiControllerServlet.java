package kr.or.ddit.servlet07;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.exception.ResponseStatusException;

@WebServlet(loadOnStartup = 1, value="/09/mbti")
public class MbtiControllerServlet extends HttpServlet{
	private ServletContext application;

	@Override
	public void init() throws ServletException {
		super.init();
		
		Map<String, String> mbitMap = new LinkedHashMap<>();
		mbitMap.put("istj","1. ISTJ 소금형");
		mbitMap.put("isfj","2. ISFJ 권력형");
		mbitMap.put("infj","3. INFJ 예언자형");
		mbitMap.put("intj","4. INTJ 과학자형");
		mbitMap.put("istp","5. ISTP 백과사전형");
		mbitMap.put("isfp","6. ISFP 성인군자형");
		mbitMap.put("infp","7. INFP 잔다르크형");
		mbitMap.put("intp","8. INTP 아이디어형");
		mbitMap.put("estp","9. ESTP 활동가형");
		mbitMap.put("esfp","10. ESFP 사교형");
		mbitMap.put("enfp","11. ENFP 스파크형");
		mbitMap.put("entp","12. ENTP 발명가형");
		mbitMap.put("estj","13. ESTJ 사업가형");
		mbitMap.put("esfj","14. ESFJ 친선도모형");
		mbitMap.put("enfj","15. ENFJ 언변능숙형");
		mbitMap.put("entj","16. ENTJ 지도자형");
		
		application = getServletContext();
		application.setAttribute("mbtiMap", mbitMap);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/mbti/mbtiForm.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> mbtiMap = (Map) application.getAttribute("mbtiMap");
		// 1. type 파라미터를 확보
		// 2. 파라미터가 검증. 필수 파라미터. 누락 시 400 에러
		// 3. type에 해당하는 페이지로 이동 할것
		try {
			String mbtiType = Optional.ofNullable(req.getParameter("type"))
									.filter(tp->!tp.isEmpty())
									.orElseThrow(()->new ResponseStatusException(400, "필수파라미터 누락"));
			
			if(!mbtiMap.containsKey(mbtiType)) {
				throw new ResponseStatusException(400, String.format("%s mbti 유형은 없음.", mbtiType));
			}
			
			String content = String.format("/WEB-INF/views/mbti/%s.html", mbtiType);
			req.setAttribute("content", content);
			String path = "/WEB-INF/views/mbti/base.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}catch(ResponseStatusException e) {
			resp.sendError(e.getStatus(), e.getMessage());
		}
	}
}
