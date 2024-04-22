package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리(CRUD)를 위한 Persistence Layer
 * 
 * 페이징 처리 단계
 * 1. totalRecord / datalist 조회할 수 있는 쿠리 : 동일한 검색조건 사용
 * 2. readXXList, retrieveXXXList 로직에서 1번의 메소드 호출
 * 3. 컨트롤러에서 현재 페이지와 검색조건을 파라미터로 획득 -> PaginationInfo 로 캡슐화
 * 4. paging HTML 구문을 생성할 paginationRenderer 객체 사용.
 * 5. view layer 에서 검색과 페이징을 위한 UI 생성(입력UI와 전송UI 분리 구조 사용이 안전)
 */
@Mapper
public interface MemberDAO {
	/**
	 * 회원 등록
	 * @param member
	 * @return 등록된 레코드 수
	 */
	public int insertMember(MemberVO member);
	/**
	 * 회원 목록 조회(아이디, 이름, 휴대폰, 주소, 이메일, 마일리지
	 * @return 존재하지 않으면, list.size() == 0
	 */
	public List<MemberVO> selectMemberList(PaginationInfo paging);
	public int selectTotalRecord(PaginationInfo paging);
	
	/**
	 * 회원 상세 조회(엔티티의 모든 컬럼 조회)
	 * @param memId
	 * @return 존재하지 않으면 null 반환
	 */
	public MemberVO selectMember(String memId);
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 등록된 레코드 수
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 정보 삭제(???)
	 * @param memId
	 * @return 삭제(???)된 레코드 수
	 */
	public int deleteMember(String memId);
	
	
	
	/**
	 * 인증시스템에서 사용할 메소드로 사용자의 (아이디, 이름, 휴대폰, 주소, 이메일)을 조회한다. 인증여부 확인
	 * @param memId
	 * @return
	 */
	public MemberVO selectMemberForAuth(@Param("memId") String memId);
	
}
