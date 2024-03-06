package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 
 * 조건1 웹상에서 요청(HTTP)을 받고(분석)
 * 조건2 웹상으로 응답(HTTP)을 전송(동적자원생성)할 수 있는 객체의 필요요건 --> HttpServlet이라고 한다.
 *
 * 개발단계
 * 1. HttpServlt의 구현체 정의
 * 2. 필요한 callback 메서드를 재정의
 * 		callback : 직접적으로 호출하지 않아도 관련된 이벤트가 발생했을때 시스템 내부적으로 자동 호출되는 구조.
 *   ex) doGet(http get method 요청 발생)
 *       doPost(http post method 요청 발생)
 *       doDelete(http delete method 요청 발생)
 *   서블릿의 생명주기 이벤트
 *   	: 서블릿 생성 및 초기화 - init
 *   		일반적으로 컨테잉너는 최초의 요처이 발생했을때 싱글턴 인스턴스를 생성함.
 *   		생성시점 변경 가능 
 *   	: 서블릿 소멸 - destroy
 *   서블릿 요청 이벤트
 *   	: service --> doXXX 계열의 메서드로 분기하여 처리한다
 * 3. WAS(Servlet Container)에게 해당 서블릿 등록.
 * 		bean container
 * 		javabean container
 * 		servlet container
 * 		jsp container
 * 		UI container : <div><button>버튼</button><span></span></div> 여기서 컨테이너는 div이다 button과 span은 컴포넌트다
 * 					   여기서 div를 지우면 안에 있던 컴포넌트도 없어진다
 * 	container : 컨테이너 내부의 객체에 대한 생명주기 관리자.
 * 	1) web.xml 의 servlet --> selvlet-name, servlet-class
 *  	load-on-startup, init-param, multipart-config 등의 엘리먼트로 컨테이너 특정 제어.
 *  2) @WebServlet : 어노테이션의 속성으로 컨테이너 특성 제어
 *  	@WebServlet : 마커 어노테이션
 *  	@WebServlet("/desc") : single-value 어노테이션(value 속성값)
 *  	@WebServlet(value = "/desc", loadOnStartup = 1) : multi-value 어노테이션(속성 이름 명시)
 * 4. 등록된 서블릿에 대한 매핑 설정. *** 절대경로로 표기함 ***
 * 	  case 1 : "/" - 정적 자원에 대한 맵핑(DefaultServlet 의 맵핑정보로 사용됨), 가능하면 사용하지 않는것이 좋다, "/"는 톰캣의 영역으로 두어줘야한다
 *    case 2 : "/*", "/member/* - 경로맵핑, 구체적인 맵핑정보를 제외한 나머지 모든 요청 처리(경로맵핑구조), 가능하면 사용하지 않는것이 좋다
 *    case 3 : "*.do" = 확장자 내핑(확장자맵핑구조)
 *    case 4 : "/member/*.do" - 이 맵핑은 사용할 수 없음, 경로 맵핑이나 확장자 맵핑 한가지만 사용가능
 */
@WebServlet(value = "/desc", loadOnStartup = 1)
public class DescriptionServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("서블릿 인스턴스 생성 후 초기화 작업 완료");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 메서드 시작");
		super.service(req, resp);
		System.out.println("service 메서드 종료");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메서드 실행");
		resp.setContentType("text/plain;charset=UTF-8");
		resp.getWriter().print("동적 응답 전송");
	}
	
	@Override
	public void destroy() {		
		super.destroy();
		System.out.println("servlet 객체 소멸");
	}
	
	
}
