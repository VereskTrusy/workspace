package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/bts")
public class btsControllerServlet extends HttpServlet{
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 서비스 전체에서 해당 map을 사용해야하기 때문에 application 레벨 스코프에서 사용한다.
		application = getServletContext();
		Map<String, String[]> btsMap = new LinkedHashMap<String, String[]>();
		btsMap.put("B001", new String[] {"뷔", "bts/bui"});
		btsMap.put("B002", new String[] {"제이홉", "bts/jhop"});
		btsMap.put("B003", new String[] {"지민", "bts/jimin"});
		btsMap.put("B004", new String[] {"진", "bts/jin"});
		btsMap.put("B005", new String[] {"정국", "bts/jungkuk"});
		btsMap.put("B006", new String[] {"알엠", "bts/rm"});
		btsMap.put("B007", new String[] {"슈가", "suga"});
		application.setAttribute("btsMap", btsMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doGet 에서는 application에 정보가 있으니깐 이동만 담당
		// /WebStudy01/src/main/webapp/WEB-INF/views/bts/btsForm.jsp
		// WEB-INF 에 있기 때문에 sendRedirect는 할 수 없다.
		String viewName = "/WEB-INF/views/bts/btsForm.jsp";
		
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// bts 자원 획득
		Map<String, String[]> map = (Map<String, String[]>) application.getAttribute("btsMap");
		// request 디코딩 설정
		req.setCharacterEncoding("UTF-8");
		
		// 쌤이 한거..
		String member = req.getParameter("member");
		int status = 200;
		if(StringUtils.isBlank(member)) {
			status = 400;
		}else if(!map.containsKey(member)) {
			status = 404;
		}
		
		if(status == 200) {
			String[] btsData = map.get(member);
			String path = btsData[1];
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			
			req.setAttribute("content", prefix + path + suffix);
			req.getRequestDispatcher("/WEB-INF/views/bts/base.jsp").forward(req, resp);
		}else {
			resp.sendError(status);
		}
		
		
		
		// mbti 예제에서 한거 복붙 ..
//		try {
//			// 널검사, 화이트스페이스검사
//			String member = Optional.ofNullable(req.getParameter("member"))
//					.filter(tp->!tp.isEmpty())
//					.orElseThrow(()->new ResponseStatusException(400, "필수파라미터 누락"));
//			// 자원 내에 파라메터가 포함되어있는지 검사
//			if(!map.containsKey(member)) {
//				throw new ResponseStatusException(400, String.format("%s 멤버 유형은 없음.", member));
//			}
//			// 파라미터에 해당하는 컨텐츠 획득
//			String content = map.get(member)[1];
//			// view로 데이터 전달
//			req.setAttribute("content", content);
//			// 해당 화면으로 이
//			String path = "/WEB-INF/views/bts/base.jsp";
//			req.getRequestDispatcher(path).forward(req, resp);
//		}catch(ResponseStatusException e) {
//			resp.sendError(e.getStatus(), e.getMessage());
//		}
		
		
		// 내가 한거..
//		String member = URLDecoder.decode(req.getParameter("member"), "UTF-8");
//		
//		if(null == member || member.isEmpty()) {
//			resp.sendError(400, "비었어");
//			return;
//		} else {
//			map = (Map<String, String[]>) application.getAttribute("btsMap");
//			if( !map.containsKey(member)) {
//				resp.sendError(404, "그거없어");
//				return;
//			}
//		}
//		
//		String content = map.get(member)[1];
//		
//		req.setAttribute("content", content);
//		req.getRequestDispatcher("/WEB-INF/views/bts/base.jsp").forward(req, resp);
		
		
	}
}




















