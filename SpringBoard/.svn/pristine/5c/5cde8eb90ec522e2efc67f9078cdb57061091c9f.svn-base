package kr.co.sample.member.vo;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberVOWrapper extends User{
	private final MemberVO realUser;

	public MemberVOWrapper(MemberVO realUser, List<GrantedAuthority> authorities) {
		super(realUser.getMemId(), realUser.getMemPass(), authorities);
		this.realUser = realUser;
	}
	
	public MemberVO getRealUser() {
		return realUser;
	}

}
