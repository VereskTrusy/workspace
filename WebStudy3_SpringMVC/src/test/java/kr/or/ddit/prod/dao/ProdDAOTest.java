package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOTest extends AbstractRootContextTest {
	
	@Autowired
	ProdDAO dao;

	@Test
	void testInsertProd() {
//		ProdVO prod = new ProdVO();
//		prod.setProdName("손선풍기");
//		prod.setProdLgu("P101");
//		prod.setProdBuyer("P10101");
//		prod.setProdCost(200L);
//		prod.setProdPrice(50000L);
//		prod.setProdSale(1000L);
//		prod.setProdOutline("작음");
//		prod.setProdImg("P101000123.gif");
//		prod.setProdTotalstock(0L);
//		prod.setProdInsdate(LocalDate.parse("2024-04-23"));
//		prod.setProdProperstock(0L);
//		prod.setProdSize("small");
//		prod.setProdDelivery("안주의");
//		prod.setProdUnit("EA");
//		prod.setProdQtyin(1L);
//		prod.setProdQtysale(0L);
//		prod.setProdMileage(0L);
		ProdVO prod2 = dao.selectProd("P101000001");
		prod2.setProdId(null);
		int rowcnt = dao.insertProd(prod2);
		
		log.info("결과 : {}", rowcnt);
		
	}

	@Test
	void testSelectProdList() {
		PaginationInfo paging =  new PaginationInfo();
		paging.setTotalRecord(dao.selectTotalRecord(paging));
		log.info("{}", paging);
		
		paging.setCurrentPage(2);
		List<ProdVO> prodList = dao.selectProdList(paging);
		log.info("list : {}", prodList);
		log.info("{}", paging);
	}

	@Test
	void testSelectProd() {
		String prodId = "P101000003";
		ProdVO prodInfo = dao.selectProd(prodId);
		log.info("prod : {}", prodInfo);
	}

	@Test
	void testUpdateProd() {
		fail("Not yet implemented");
	}

}
