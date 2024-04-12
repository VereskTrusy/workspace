package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * C : /member/memberInsert.do
 * 		Get
 * 		Post
 * R - 단건 : /member/memberDetail.do?who=a001
 * 		Get 
 * R - 다건 : /member/memberList.do
 * 		Get
 * U : /member/memberUpdate.do
 * 		Get
 * 		Post
 * D : /member/memberDelete.do
 * 		Post
 * 
 * 등록되어 있는 로거를 받아오면 해당 로거의 등급으로 기록한다.
 * lombok으로 
 * private static final Logger logger = LoggerFactory.getLogger(MemberListControllerServlet.class);
 */
@Slf4j // 롬복 로그 생성
@Controller
@RequiredArgsConstructor
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	
	@Autowired
	private final MemberService service; // 서비스
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		logger.info("컨트롤러 동작");
		log.info("컨트롤러 동작"); // 롬복으로 생성된 log 사용
		
		// 멤버 조회를 위한 리스트의 폼으로 이동
		List<MemberVO> memberList = service.retriveMemberList();
//		logger.info("조회된 모델 : {}", memberList); // {} : 메시지 아규먼트
		log.info("조회된 모델 : {}", memberList); // 롬복으로 생성된 log 사용
		
		// scope 결정
		req.setAttribute("memberList", memberList);
		
		// view 결정
		// flow control
		String viewName = null;
		viewName = "member/memberList_bak";
		new ViewResolverComposite().resolveView(viewName, req, resp);	
	}
}
