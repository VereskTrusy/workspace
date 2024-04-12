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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

/**
 * Controller에서 프로세스 흐름
 * 1. 요청 접수, 분석 
 * 2. 검증 
 * 3. 로직 사용(model 확보) 
 * 4. scope를 이용하여 model 공유 
 * 5. view 결정 
 * 6. view 로 이동(flow Control)
 */
@Controller
@RequiredArgsConstructor
@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {
	
	@Autowired
	private final MemberService service;

	// 회원 가입 폼으로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, InsertGroup.class); // errors -> call by referense 
		
		String viewName = null;
		if (errors.isEmpty()) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디중복");
				viewName = "member/memberForm";
				break;
			case FAIL: 
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
			viewName = "member/memberForm";
		}
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
