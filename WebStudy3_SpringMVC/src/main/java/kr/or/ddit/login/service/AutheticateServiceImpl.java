package kr.or.ddit.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutheticateServiceImpl implements AuthenticateService{
	
	@Autowired
	private final MemberDAO dao;

	@Override
	public MemberVO authenticate(MemberVO inputData) throws AuthenticateException {
		// 확인 해야될 상황
		// 사용자가 없는 경우 , UserNotFoundException
		// 사용자의 비밀번호가 틀린경우 , BadCredentialException
		// 사용자의 아이디/비밀번호를 재확인 해야하는 경우 , AuthenticateException
		// 응집력을 높이기 위해서 AutheticateService를 만든것이다.
		// dao에 해야할 책임이 많기 때문이고,
		// 해야할 일을 분활하고, 변경사항에 대응하기 위함이다.
		
		// 사용자 존재 확인
		MemberVO saved = dao.selectMemberForAuth(inputData.getMemId());
		if(saved == null) {
			throw new UserNotFoundException(String.format("%s 사용자 없음", inputData.getMemId()));
		}
		
		// 탈퇴한 유저인지 확인
		if(saved.isMemDelete()) {
			throw new AuthenticateException("이미 탈퇴한 회왼");
		}
		
		// 비밀번호 인증 로직 변경할것
		String savedPass = saved.getMemPass(); // 암호화된 상태
		String inputPass = inputData.getMemPass();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		if(encoder.matches(inputPass, savedPass)) {
			// 정상처리
			return saved;
		}else {
			throw new BadCredentialException("비밀번호 오류");
		}
	}
	
}
/*

비밀번호 : 단방향 암호화 구조
주민번호 : 양방향 암호화 구조

*/