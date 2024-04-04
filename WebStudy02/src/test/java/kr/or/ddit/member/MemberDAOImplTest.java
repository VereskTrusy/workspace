package kr.or.ddit.member;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MemberDAOImplTest {
	MemberDAOImpl dao = new MemberDAOImpl();
	
	@Test
	void testInsertMember() {
		MemberVO member = new MemberVO();
		//assertThrows(PersistenceException.class, ()->dao.insertMember(member));
		
		member.setMemId("a006");
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
	void testUpdateMember() {
		MemberVO member = new MemberVO();
		member.setMemId("a002");
		// nullable 요소들에 null이 들어갔을때 에러가 발생 할거다 
		assertThrows(PersistenceException.class, ()->dao.updateMember(member));
		
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("졸려엇2");
		member.setMemZip("00000");
		member.setMemAdd1("대전 오류");
		member.setMemAdd2("ㅇㄹㅇㄹ");
		member.setMemMail("aa@naver.com");
		member.setMemBir("2024-01-01");
		int rowcnt = dao.updateMember(member);
		assertEquals(1, rowcnt);
	}
	
	@Test
	void testDeleteMember() {
		MemberVO member = new MemberVO();
		member.setMemId("a0012341234123"); // 없는 아이디
		int rowcnt = dao.deleteMember(member.getMemId());
		assertNotEquals(1, rowcnt);
		
		member.setMemId("a002"); // 있는 아이디
		rowcnt = dao.deleteMember(member.getMemId());
		
		assertEquals(1, rowcnt);
	}
	
	@Test
	void testSelectMemberList() {
		List<MemberVO> memberlist = dao.selectMemberList();
		assertNotNull(memberlist);
		assertNotEquals(0, memberlist);
		log.info("list : {}", memberlist);
	}

	@Test
	void testSelectMember() {
		String memId = "a001";
		MemberVO member = dao.selectMember(memId);
		log.info("cartList : {}", member.getCartList().size());
//		assertNotNull(member);
//		memId = "asdfas' OR '1'='1";
//		assertNull(dao.selectMember(memId));
	}
}
