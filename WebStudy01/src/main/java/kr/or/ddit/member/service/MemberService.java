package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface MemberService {
	/**
	 * 회원 목록 조회(아이디, 이름, 휴대폰, 주소, 이메일, 마일리지
	 * @return 존재하지 않으면, list.size() == 0
	 */
	List<MemberVO> selectMemberList();
}
