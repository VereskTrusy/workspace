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
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
  조건 : 의사코드 작성
  MemberInsertControllerServlet 를 보고 어떤 코드가 중복되는지 확인 할것
  
  doGet 메서드로 요청이 들어 옴
  request의 캐릭터 셋을 UTF-8로 맞춘다.
  파라미터로 회원아이디를 받는다
  파라미터의 회원아이디가 null인지 검사한다.
  파라미터가 null이 아니라면 현재 등록되어있는지 조회한다.
  		서비스를 실행하여 단일 회원 조회 후 있다면 로직을 계속 수행한다
  			memberVo에 파라미터로 받은 정보를 받는다. 
  		단일 회원이 존재 하지 않으면 잘못된 요청에 대한 처리를 진행한다.
  리퀘스트에 회원정보를 저장한다.
  view 수정폼으로 포워드 이동한다
  
  
  doPost 메서드로 요청이 들어 옴
  파라미터로 회원정보를 받는다
  파라미터의 각 필수정보가 null인지 검사한다.
  파라미터가 null이 아니라면 현재 등록되어있는지 조회한다.
  파라미터 중 아이디,패스워드 로 서비스 조회(단일회원 조회) 
  		일치 -> memberVo에 파라미터로 받은 정보를 받는다.
  		불일치 -> 단일 회원이 존재 하지 않으면 잘못된 요청에 대한 처리를 진행한다.
  파라미터 가 담긴 memberVo를 update 서비스를 사용하여 처리한다.
  		업데이트 결과
  			성공 시 성공메시지를 세션에 저장한다.
  			실패 시 실패 메시지를 리퀘스트에 담는다

**/
@Slf4j
@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();

	// 회원 가입 폼으로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getUserPrincipal().getName();
		HttpSession session = req.getSession();
		MemberVO member = service.retriveMember(memId);
		req.setAttribute("member", member);
		 String viewName = "member/memberForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

	// 회원 가입 진행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO(); // command Object
		req.setAttribute("member", member);
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(member, parameterMap);
		
		// 2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = ValidateUtils.validate(member, errors); // errors -> call by referense 
		req.setAttribute("errors", errors);
		
		// 3. 로직 사용(model 확보)
		// 4) 밸리데이션 검증 실패
		String viewName = null;
		if (errors.isEmpty()) { // 에러가 없다 == 검증이 끝났다.
			// 4. scope를 이용하여 model 공유
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호가 잘못됨");
				viewName = "member/memberForm";
				break;
			case FAIL: // 현재는 fail상황 없음. 그보다 먼저 SQLException 발생, 현티어구조때문임. 
				req.setAttribute("message", "서버오류");
				viewName = "member/memberForm";
				break;
			default: 
//				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/mypage";
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
