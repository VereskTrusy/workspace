package kr.or.ddit.login.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

class AuthenticateServiceTest extends AbstractRootContextTest {
	
	@Test
	void testAuthenticateUserNotFound() {
		
		@Autowired
		AuthenticateService authService;
		
		// 사용자가 없는 경우 , UserNotFoundException
		final MemberVO inputData = new MemberVO(); // final 사용 이유 method Area 의 constante pool에 만들기 위함
		inputData.setMemId("ringddingdong");
		
		assertThrows(UserNotFoundException.class, new Executable() { // 얘랑 저장위치가 다르기때문임
			
			// 익명 객체 안에 하나의 메서드만 존재하ㄹ 때 익명 ㅁㄴㅇㄻㄴㅇ ㄻㄴㅇㄻㄴㅇㄹ ㅁㄴㄹ
			@Override
			public void execute() throws Throwable {
				authService.authenticate(inputData);
			}
		});
	}
	
	
	@Test
	void testAuthenticateBadCredential() {
		@Autowired
		AuthenticateService authService;
		
		// 사용자의 비밀번호가 틀린경우 , BadCredentialException
		final MemberVO inputData = new MemberVO();
		inputData.setMemId("b001");
		inputData.setMemPass("asdfgwteasd");
		assertThrows(BadCredentialException.class, new Executable() { 
			
			@Override
			public void execute() throws Throwable {
				authService.authenticate(inputData);
			}
		});
	}
	
	
	@Test
	void testAuthenticate() {
		
		@Autowired
		AuthenticateService authService;
		
		// 사용자의 아이디/비밀번호를 재확인 해야하는 경우 , AuthenticateException
		final MemberVO inputData = new MemberVO();
		inputData.setMemId("b001");
		inputData.setMemPass("1004");
		assertNotNull(authService.authenticate(inputData));
		
	}
}
