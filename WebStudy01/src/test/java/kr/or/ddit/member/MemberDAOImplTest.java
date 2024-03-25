package kr.or.ddit.member;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

class MemberDAOImplTest {
	MemberDAOImpl dao = new MemberDAOImpl();
	
	@Test
	void testInsertMember() {
		MemberVO member = new MemberVO();
		assertThrows(PersistenceException.class, ()->dao.insertMember(member));
		
//		MEM_ID
//		MEM_PASS
//		MEM_NAME
//		MEM_ZIP
//		MEM_ADD1
//		MEM_ADD2
//		MEM_MAIL
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("테스트");
		member.setMemZip("00000");
		member.setMemAdd1("대전 오류");
		member.setMemAdd2("ㅇㄹㅇㄹ");
		member.setMemMail("aa@naver.com");
		member.setMemBir("2024-01-01");
		int rowcnt = dao.insertMember(member);
		assertEquals(1, rowcnt);
	}
	
	@Test
	void testSelectMemberList() {
		List<MemberVO> memberlist = dao.selectMemberList();
		assertNotNull(memberlist);
		assertNotEquals(0, memberlist);
		System.out.println(memberlist);
	}

	@Test
	void testSelectMember() {
		String memId = "a001";
		MemberVO member = dao.selectMember(memId);
		assertNotNull(member);
		System.out.println(member);
		memId = "asdfas' OR '1'='1";
		assertNull(dao.selectMember(memId));
	}
}
