package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 등록된 레코드 수
	 */
	int updateMember(MemberVO member);
	
	/**
	 * 회원 정보 삭제(???)
	 * @param memId
	 * @return 삭제(???)된 레코드 수
	 */
	int deleteMember(String memId);
	
	
	
	/**
	 * 인증시스템에서 사용할 메소드로 사용자의 (아이디, 이름, 휴대폰, 주소, 이메일)을 조회한다. 인증여부 확인
	 * @param memId
	 * @return
	 */
	public MemberVO selectMemberForAuth(@Param("memId") String memId);
	
}
