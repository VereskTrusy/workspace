package kr.or.ddit.buyer.dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class BuyerDAOTest extends AbstractRootContextTest {
	
	@Autowired
	BuyerDAO dao;
	
	@Test
	void testSelectBuyerList() {
		List<BuyerVO> list = dao.selectBuyerList();
		list.forEach(b->log.info("buyer : {}", b));
	}

	@Test
	void testSelectBuyer() {
		BuyerVO buyer = dao.selectBuyer("P10101");
		log.info("buyer 조회{}", buyer);
	}

}
