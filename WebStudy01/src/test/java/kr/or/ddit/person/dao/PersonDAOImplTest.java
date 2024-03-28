package kr.or.ddit.person.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.PersonVo;

class PersonDAOImplTest {
	PersonDAO dao = new PersonDAOImpl();

	@Test
	void testSelectPersonList() {
		List<PersonVo> people = dao.selectPersonList();
		System.out.println(people.size());
	}

	@Test
	void testSelectPerson() {
		// assertion 구조를 통해 테스트 결과를 추정하는거
		assertNotNull(dao.selectPerson("A0011")); // 널이 아님을 추정함
	}

	@Test
	void testSelectPersonNotExist() {
		// assertion 구조를 통해 테스트 결과를 추정하는거
		assertNull(dao.selectPerson("asfdga"));  // 널임을 추정함
	}
}
