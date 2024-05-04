package kr.or.ddit.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping(value="/accessError")
	public void accessDenied(Authentication auth, Model model) {
		System.out.println("access Denied : " + auth);
		// auth의 출력 정보
//		org.springframework.security.authentication.UsernamePasswordAuthenticationToken@8ea115: 
//			Principal: org.springframework.security.core.userdetails.User@bfc28a9a: 
//		Username: member; 
//		Password: [PROTECTED]; 
//		Enabled: true; 
//		AccountNonExpired: true; 
//		credentialsNonExpired: true; 
//		AccountNonLocked: true; 
//		Granted Authorities: ROLE_MEMBER; 
//		Credentials: [PROTECTED]; 
//		Authenticated: true; 
//		Details: org.springframework.security.web.authentication.WebAuthenticationDetails@166c8: 
//			RemoteIpAddress: 0:0:0:0:0:0:0:1; 
//		SessionId: B408B779E01778F57820920B581277AB; 
//		Granted Authorities: ROLE_MEMBER
		
		model.addAttribute("msg", "Access Denied");
	}
	
}
