package kr.or.ddit.lprod.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class LprodDAOTest {
	LprodDAO dao = new LprodDAOImpl();
	
	@Test
	void testSelectLprodList() {
		List<LprodVO> list = dao.selectLprodList();
		list.forEach((l)->log.info("lprod : {}", l));
		log.info("list : {} ", list);
		
		List<BuyerVO> buyerList = new ArrayList<BuyerVO>();
		for(LprodVO lprod : list) {
			buyerList.addAll(lprod.getBuyerList());
		}
		
		buyerList.forEach(b->log.info("buyer : {}", b));
	}

}
