package kr.or.ddit.mapper;

import kr.or.ddit.vo.MemberVO;

public interface LoginMapper {
	public MemberVO loginCheck(MemberVO memberVO);
	public int signup(MemberVO memberVO);
	public void addAuth(MemberVO memberVO);
	public MemberVO idCheck(String memId);
	public MemberVO nickNameCheck(String memNickname);
	
	public MemberVO readByUserId(String username);
}
