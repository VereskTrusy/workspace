package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리(CRUD)를 위한 Persistence Layer
 * 
 *
 */
public interface MemberDAO {
	/**
	 * 회원 등록
	 * @param member
	 * @return 등록된 레코드 수
	 */
	int insertMember(MemberVO member);
	/**
	 * 회원 목록 조회(아이디, 이름, 휴대폰, 주소, 이메일, 마일리지
	 * @return 존재하지 않으면, list.size() == 0
	 */
	List<MemberVO> selectMemberList();
	
	/**
	 * 회원 상세 조회(엔티티의 모든 컬럼 조회)
	 * @param memId
	 * @return 존재하지 않으면 null 반환
	 */
	MemberVO selectMember(String memId);
//	update
//	delete
}