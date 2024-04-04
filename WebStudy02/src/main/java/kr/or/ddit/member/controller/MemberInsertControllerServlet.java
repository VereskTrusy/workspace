package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;

/**
 * Controller에서 프로세스 흐름
 * 1. 요청 접수, 분석 
 * 2. 검증 
 * 3. 로직 사용(model 확보) 
 * 4. scope를 이용하여 model 공유 
 * 5. view 결정 
 * 6. view 로 이동(flow Control)
 */
@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	// 회원 가입 폼으로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "member/memberForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

	// 회원 가입 진행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 접수, 분석
		req.setCharacterEncoding("UTF-8");
		
		MemberVO member = new MemberVO(); // command Object
		req.setAttribute("member", member);
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(member, parameterMap);
		
		// 2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = ValidateUtils.validate(member, errors, InsertGroup.class); // errors -> call by referense 
		req.setAttribute("errors", errors);
		
		// 3. 로직 사용(model 확보)
		// 검증 실패/정상 시나리오를 통해 분기 시킴
		// 검증 성공, 서비스 실행 결과 반환값에 따른 분기
		// 1) 아이디 중복 - PKDUPLICATED
		// 2) 서버오류 - FAIL
		// 3) 정상처리 - OK
		//    -> Service doc주석에 명시 해놓음
		// 검증 실패
		// 4) 밸리데이션 검증 실패
		String viewName = null;
		if (errors.isEmpty()) { // 에러가 없다 == 검증이 끝났다.
			// 4. scope를 이용하여 model 공유
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디중복");
				viewName = "member/memberForm";
				break;
			case FAIL: // 현재는 fail상황 없음. 그보다 먼저 SQLException 발생, 현티어구조때문임. 
				req.setAttribute("message", "서버오류");
				viewName = "member/memberForm";
				break;
			default: 
				viewName = "redirect:";
				break;
			}
		} else {
			req.setAttribute("member", member);
			req.setAttribute("errors", errors);
			viewName = "member/memberForm"; // 리퀘스트에
		}
		
		// 5. view 결정
		// 6. view 로 이동(flow Control)
		// Front Controller Pattern 을 통해서 하위의 모든 컨트롤러에서 중복되는 코드를 해결할 예정.
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
