package kr.or.ddit.controller.conn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.ILoginService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Inject
	private ILoginService loginService;
	private HttpSession session;
	
	@Inject
	private PasswordEncoder pe;
	
	@PostConstruct
	public void init() {
		log.info("########## " + pe.encode("1234"));
	}
	
	@RequestMapping(value="/signin.do", method = RequestMethod.GET)
	public String signIn() {
		return "conn/signin";
	}
	
	@RequestMapping(value="/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(
			HttpServletRequest req,
			RedirectAttributes ra,
			MemberVO memberVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
			
		if(StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if(StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("memberVO", memberVO);
			goPage = "conn/signin";
		}else {
			MemberVO member = loginService.loginCheck(memberVO);
			if(member != null) {
				session = req.getSession();
				session.setAttribute("SessionInfo", member);
				ra.addFlashAttribute("message", member.getMemName() + "님, 환영합니다!");
				goPage = "redirect:/board/list.do";
			}else {
				model.addAttribute("message", "서버에러, 다시 시도해주세요.");
				model.addAttribute("memberVO", memberVO);
				goPage = "conn/signin";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value="/signup.do", method = RequestMethod.GET)
	public String signUpForm() {
		return "conn/signup";
	}
	
	@RequestMapping(value="/signup.do", method = RequestMethod.POST)
	public String signUp(
			RedirectAttributes ra,
			MemberVO memberVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
			
		if(StringUtils.isBlank(memberVO.getMemId())) {
			errors.put("memId", "아이디를 입력해주세요.");
		}
		if(StringUtils.isBlank(memberVO.getMemPw())) {
			errors.put("memPw", "비밀번호를 입력해주세요.");
		}
		if(StringUtils.isBlank(memberVO.getMemName())) {
			errors.put("memName", "이름을 입력해주세요.");
		}
		if(StringUtils.isBlank(memberVO.getMemNickname())) {
			errors.put("memNickname", "닉네임을 입력해주세요.");
		}
		
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("memberVO", memberVO);
			goPage = "conn/signup";
		}else {
			memberVO.setMemPw(pe.encode(memberVO.getMemPw()));
			ServiceResult result = loginService.signup(memberVO);
			if(result.equals(ServiceResult.OK)) {
				ra.addFlashAttribute("message", "회원가입이 완료되었습니다!");
				goPage = "redirect:/signin.do";
			}else {
				model.addAttribute("message", "서버에러, 다시 시도해주세요.");
				model.addAttribute("memberVO", memberVO);
				goPage = "conn/signup";
			}
		}
		return goPage;
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> idCheck(@RequestBody Map<String, String> map){
		ServiceResult result = loginService.idCheck(map.get("memId"));
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/nickNameCheck.do", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> nickNameCheck(@RequestBody Map<String, String> map){
		ServiceResult result = loginService.nickNameCheck(map.get("memNickname"));
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	// ################################ 시큐리티 적용 후 로그인페이지
	@RequestMapping(value="/security/login.do", method = RequestMethod.GET)
	public String securityLogin() {
		return "security/signin";
	}
	
}
