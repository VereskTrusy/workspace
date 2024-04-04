package kr.or.ddit.member.service;

import java.util.List;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AutheticateServiceImpl;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	// 의존관계 설정
	private MemberDAOImpl dao = new MemberDAOImpl();
	private AuthenticateService authService = new AutheticateServiceImpl();
	
	private void encryptMember(MemberVO member) {
		String plain = member.getMemPass();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode(plain);
		member.setMemPass(encoded);
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		
		if(dao.selectMember(member.getMemId()) == null) {
			encryptMember(member);
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retriveMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		return memberList;
	}

	@Override
	public MemberVO retriveMember(String memId) throws PkNotFoundException {
		MemberVO member = dao.selectMember(memId);
		
		// member가 null로 예외가 발생하더라도
		// 에러가 아닌 에러코드를 던질라고~
		if(member == null)
			throw new PkNotFoundException(500);
		return member;
	}

//	@Override
//	public ServiceResult modifyMember(MemberVO member) throws PkNotFoundException {
//		ServiceResult result = null;
//		MemberVO srchMember = new MemberVO();
//		
//		if(dao.selectMember(member.getMemId()) != null) {
//			// 회원은 잇음
//			srchMember = dao.selectMember(member.getMemId());
//			if(member.getMemPass().equals(srchMember.getMemPass())) {
//				result = ServiceResult.OK;
//			}else {
//				result = ServiceResult.INVALIDPASSWORD;
//			}
//		} else {
//			// 회원 없음
//			result = ServiceResult.FAIL;
//		}
//		
//		return result;
//	}
	@Override
	public ServiceResult modifyMember(MemberVO member) throws PkNotFoundException {
		
		try {
			authService.authenticate(member);
			return dao.updateMember(member) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch(BadCredentialException e) {
			return ServiceResult.INVALIDPASSWORD;
		}
		
//		MemberVO saved = retriveMember(member.getMemId());
//		ServiceResult result = null;
//		
//		if(saved.getMemPass().equals(member.getMemPass())) {
//			return dao.updateMember(member) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
//		}else {
//			result = ServiceResult.INVALIDPASSWORD;
//		}
//		
//		return result;
	}
	

	@Override
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException {
		
		try {
			authService.authenticate(inputData);
			return dao.deleteMember(inputData.getMemId()) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch(BadCredentialException e) {
			return ServiceResult.INVALIDPASSWORD;
		}
		
		
//		// 조회해서 아이디가 존재하지 않으면 PkNotFoundException 예외발생
//		MemberVO saved = retriveMember(inputData.getMemId()); 
//		ServiceResult result = null;
//		
//		// 기존 데이터와 입력된 데이터의 패스워드가 같은지 검사
//		if(saved.getMemPass().equals(inputData.getMemPass())) {
//			return dao.deleteMember(inputData.getMemId()) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
//		}else {
//			result = ServiceResult.INVALIDPASSWORD;
//		}
//		return result;
	}
	
	
}
