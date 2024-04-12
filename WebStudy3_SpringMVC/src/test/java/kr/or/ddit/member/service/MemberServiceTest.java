package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.MemberVO;

class MemberServiceTest extends AbstractRootContextTest {
	@Autowired
	MemberService service;

	@Test
	void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test
	void testRetriveMemberList() {
		service.retriveMemberList();
	}

	@Test
	void testRetriveMember() {
		fail("Not yet implemented");
	}

	@Test
	void testModifyMember() {
		// 테스트 케이스 먼저 만들고 해당 케이스에 맞춰서 개발하는 방법론, TDD 테스트 드리븐 디벨롭먼트
		// 아이디가 존재하는지 확인 , 없으면 예외를 던진다.
		MemberVO member = new MemberVO();
		member.setMemId("adfasdfa");
		assertThrows(PkNotFoundException.class, ()->service.modifyMember(member));
		
		// 비밀번호가 맞지 않는 경우
		member.setMemId("a001");
		member.setMemPass("asdfasdfasdf");
		assertEquals(ServiceResult.INVALIDPASSWORD, service.modifyMember(member));
		
		// 비밀번호가 통과된후 
		MemberVO memberOk = service.retriveMember(member.getMemId());
		assertEquals(ServiceResult.OK, service.modifyMember(memberOk));
	}

	// 멤버 삭제
	@Test
	void testRemoveMember() {
		final MemberVO member = new MemberVO();
		// 아이디가 존재하는지 확인 , 없으면 예외를 던진다.
		member.setMemId("adfasdfa");
		assertThrows(PkNotFoundException.class, ()->service.removeMember(member));
		
		// 비밀번호가 맞지 않는 경우
		member.setMemId("a001");
		member.setMemPass("asdfasdfasdf");
		assertEquals(ServiceResult.INVALIDPASSWORD, service.removeMember(member));
		
		// 비밀번호가 통과된후
		member.setMemId("a001");
		member.setMemPass("asdfasdf");
		//member = service.retriveMember(member.getMemId());
		assertEquals(ServiceResult.OK, service.removeMember(member));
	}

}
