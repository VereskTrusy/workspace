package kr.or.ddit.prod.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.AbstractModelContextTest;

// 상속 받았기 때문에 @SpringJUnitConfig 가 있는 것과 같다.
class ProdDAOTest extends AbstractModelContextTest {
	
	@Autowired
	ProdDAO dao;

	@Test
	void test() {
		dao.selectProdList();
	}

}
