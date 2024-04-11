package kr.or.ddit.buyer.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractModelContextTest;

class BuyerDAOTest extends AbstractModelContextTest {

	@Resource(name = "buyerDAO")
	public BuyerDAO dao;
	
	@Test
	void testSelectBuyerList() {
		dao.selectBuyerList(); 
	}

}
