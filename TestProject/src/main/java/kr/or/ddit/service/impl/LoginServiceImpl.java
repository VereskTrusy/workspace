package kr.or.ddit.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mapper.LoginMapper;
import kr.or.ddit.service.ILoginService;
import kr.or.ddit.vo.MemberVO;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Inject
	private LoginMapper mapper;
	
	/**
	 * <p>로그인 검증</p>
	 */
	@Override
	public MemberVO loginCheck(MemberVO memberVO) {
		return mapper.loginCheck(memberVO);
	}

	/**
	 * <p>회원가입</p>
	 */
	@Override
	public ServiceResult signup(MemberVO memberVO) {
		ServiceResult result = null;
		int status = mapper.signup(memberVO);
		if(status > 0) {
			mapper.addAuth(memberVO);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public ServiceResult idCheck(String memId) {
		ServiceResult result = null;
		MemberVO memberVO = mapper.idCheck(memId);
		if(memberVO != null) {
			result = ServiceResult.EXIST;
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

	@Override
	public ServiceResult nickNameCheck(String memNickname) {
		ServiceResult result = null;
		MemberVO memberVO = mapper.nickNameCheck(memNickname);
		if(memberVO != null) {
			result = ServiceResult.EXIST;
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
