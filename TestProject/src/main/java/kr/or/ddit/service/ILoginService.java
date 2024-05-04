package kr.or.ddit.service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public interface ILoginService {
	public MemberVO loginCheck(MemberVO memberVO);
	public ServiceResult signup(MemberVO memberVO);
	public ServiceResult idCheck(String memId);
	public ServiceResult nickNameCheck(String memNickname);
}
