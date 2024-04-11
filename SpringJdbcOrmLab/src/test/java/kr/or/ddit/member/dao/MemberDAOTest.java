package kr.or.ddit.member.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.AbstractModelContextTest;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MemberDAOTest extends AbstractModelContextTest {

	@Autowired
	MemberDAO dao;
	
	@Test
	void testSelectMemberList() {
		List<MemberVO> list = dao.selectMemberList();
		log.info("주입된 DAO : {}", list);
	}

	@Test
	void testSelectMember() {
		
	}

}
// 맵핑파일 특정위치 복사
// 다오를 빈으로 등록
// 컨테이너로부터 인젝션 받아서 실행
