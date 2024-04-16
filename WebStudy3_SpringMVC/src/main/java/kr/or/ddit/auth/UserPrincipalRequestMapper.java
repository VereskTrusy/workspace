package kr.or.ddit.auth;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

public class UserPrincipalRequestMapper extends HttpServletRequestWrapper{

	public UserPrincipalRequestMapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Principal getUserPrincipal() {
		HttpSession session = getSession(false);
		if(session != null) {
			MemberVO authMember = (MemberVO) session.getAttribute("authMember"); // login / non-login 상태
			if(authMember != null) {
				return new MemberVOWrapper(authMember);
			}
		}
		
		return super.getUserPrincipal();
	}
}
