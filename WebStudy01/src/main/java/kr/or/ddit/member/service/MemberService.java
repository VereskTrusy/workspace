package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리(CRUD) 를 위한 Business Logic Layer(Service Layer)
 * 
 *
 */
public interface MemberService {
	
	/**
	 * 회원 가입
	 * @param member
	 * @return PKDUPLICATED(중복여부), OK(성공), FAIL(실패)
	 */
	public ServiceResult createMember(MemberVO member); // 성공, 실패, 아이디중복 의 집합객체 형태로 반환하면 명확해진다. ServiceResult
	
	/**
	 * 회원 목록 조회(아이디, 이름, 휴대폰, 주소, 이메일, 마일리지
	 * @return 존재하지 않으면, list.size() == 0
	 */
	public List<MemberVO> retriveMemberList();
	
	/**
	 * 회원 정보 상세 조회
	 * @param memId
	 * @return
	 * @throws PkNotFoundException(500error) 존재하지 않는 경우 예외 발생. 
	 */
	public MemberVO retriveMember(String memId) throws PkNotFoundException;
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return INVALEDPASSWORD(인증실패,비밀번호 오류), OK, FAIL
	 * @throws PkNotFoundException(500error) 존재하지 않는 경우 예외 발생. 
	 */
	public ServiceResult modifyMember(MemberVO member) throws PkNotFoundException;
	
	/**
	 * 회원 탈퇴 
	 * @param inputData(인증용 아이디, 비밀번호)
	 * @return INVALEDPASSWORD(인증실패,비밀번호 오류), OK, FAIL
	 * @throws PkNotFoundException(500error) 존재하지 않는 경우 예외 발생. 
	 */
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException;
}
