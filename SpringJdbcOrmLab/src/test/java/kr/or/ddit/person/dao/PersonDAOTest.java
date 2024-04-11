package kr.or.ddit.person.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractModelContextTest;


class PersonDAOTest extends AbstractModelContextTest {
	
	@Autowired
	PersonDAO dao;

	@Test
	void testSelectPersonList() {
		dao.selectPersonList();
	}

	@Test
	void testSelectPerson() {
		dao.selectPerson("M0014");
	}

}
