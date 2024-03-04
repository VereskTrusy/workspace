package kr.or.ddit.servlet03;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


/**
 * 서블릿 스펙 제공 객체(제일 많이 사용되는 객체들)
 * 1. HttpServletRequest :	http 프로토콜로 발생한 요청과 해당 요청을 발생시킨 클라이언트에 대한 정보를 가진 객체이다
 * 							stateless 객체(1:1로 응답이 전송된 후 제거되는 객체이다) 
 * 							상태가 없다. 상태를 유지하기 위한 또다른 방법이 필요하다 ->    Session, Cookie 사용
 * 2. HttpServletResponse :	응답으로 전송할 컨텐츠와 해당 컨텐츠에 대한 메타데이터를 가진 객체.
 * 3. HttpSession : statefull
 * 					한 클라이언트에 대해 독립적으로 운영되는 객체(서버의 저장구조로 유지되는 객체)
 * 					서버에 세션 저장
 * 4. ServletContext :	하나의 서버(was, Servletcontainer)와 현재 컨텍스트(하나의 어플리케이션을의미)에 대한 정보를 가진 객체(싱글톤 객체)이다
 * 						서버 전체를 통틀어 하나의 서블릿컨텍스트만 사용된다면 서블릿컨텍스트로 정보를 공유가능하다, 마치 데이터베이스처럼 저장가능한 저장소이다
 * 		
 * 5. ServletConfig :	서블릿 컨테이너에 등록된 서블릿과 1:1 관계로 생성되는 객체이다
 * 						해당 서블릿의 메타데이터를 가진 객체이다
 * 						ex) servletname, initParameter
 * 						서블릿과 1대1관계이다.
 * 6. Cookie :	클라이언트의 저장구조를 통해 상태가 유지되는 객체(statefull)
 * 		파일 시스템에 저장
 *
 * 스코프 : 세션, 쿠키, 서블릿컨텍스트 객체를 말한다
 */
@WebServlet(value="/nouse", loadOnStartup = 1)
public class ServletSpecDescServlet extends HttpServlet {
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		System.out.printf("contextPath : %s\n", application.getContextPath()); // contextPath : /WebStudy01
		System.out.printf("session tracking mode : %s\n", application.getDefaultSessionTrackingModes()); // session tracking mode : [COOKIE, URL] -> 쿠키가 없으면 세션이 유지가 안된다.
		System.out.printf("servlet spec version : %d.%d\n", application.getEffectiveMajorVersion(), application.getEffectiveMinorVersion()); // servlet spec version : 3.1 , 서블릿 스펙버전 확인가능, 파일업로드시part라는거 사용하는데 이건 서블릿 스펙버전 3버전 이상에서 사용하기때문에 확인 해봐야한다
		System.out.printf("Server Info : %s\n", application.getServerInfo()); // Server Info : Apache Tomcat/8.5.99
	}
	
	
}
