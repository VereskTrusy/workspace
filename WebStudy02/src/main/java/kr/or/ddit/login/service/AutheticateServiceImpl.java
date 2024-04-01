package kr.or.ddit.login.service;

import java.rmi.UnexpectedException;

import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AutheticateServiceImpl implements AuthenticateService{
	private MemberDAO dao = new MemberDAOImpl();

	@Override
	public MemberVO authenticate(MemberVO inputData) throws AuthenticateException {
		// 확인 해야될 상황
		// 사용자가 없는 경우 , UserNotFoundException
		// 사용자의 비밀번호가 틀린경우 , BadCredentialException
		// 사용자의 아이디/비밀번호를 재확인 해야하는 경우 , AuthenticateException
		
		MemberVO saved = dao.selectMemberForAuth(inputData.getMemId());
		if(saved == null) {
			throw new UserNotFoundException(String.format("%s 사용자 없음", inputData.getMemId()));
		}
		if(saved.isMemDelete()) {
			throw new AuthenticateException("이미 탈퇴한 회왼");
		}
		String savedPass = saved.getMemPass();
		String inputPass = inputData.getMemPass();
		if(savedPass.equals(inputPass)) {
			// 정상처리
			return saved;
		}else {
			throw new BadCredentialException("비밀번호 오류");
		}
	}
	
}
