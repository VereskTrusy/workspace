package kr.or.ddit.vo;

import java.security.Principal;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberVOWrapper implements Principal {
	private MemberVO realUser; // adaptee

	// 어댑터는 기본생성자를 가지고 있으면 안된다.
	
	public MemberVOWrapper(MemberVO realUser) {
		super();
		this.realUser = realUser;
	}

	@Override
	public String getName() {
		return realUser.getMemId();
	}

}
