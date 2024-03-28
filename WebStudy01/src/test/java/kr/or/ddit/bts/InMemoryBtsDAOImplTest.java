package kr.or.ddit.bts;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.bts.dao.InMemoryBtsDAOImpl;
import kr.or.ddit.vo.BtsVO;

class InMemoryBtsDAOImplTest {

	// selectBts test
	@Test
	void selectBts() {
		InMemoryBtsDAOImpl dao = new InMemoryBtsDAOImpl();
		BtsVO vo = dao.selectBts("B001");
		assertNotNull(vo);
		assertNull(dao.selectBts("B999"));
		System.out.println(
			  "code : " + vo.getCode() 
			+ " name : " + vo.getName() 
			+ " path : " + vo.getPath()
			+ " count : " + vo.getCount()
			);
	}

	// selectBts test
	@Test
	void selectBtsList() {
		InMemoryBtsDAOImpl dao = new InMemoryBtsDAOImpl();
		List<BtsVO> list = dao.selectBtsList();
		
		for(BtsVO vo : list) {
			System.out.println(
			  "code : " + vo.getCode() 
			+ " name : " + vo.getName() 
			+ " path : " + vo.getPath());
		}
		
	}
	
	// incrementHit test
		@Test
		void incrementHit() {
			InMemoryBtsDAOImpl dao = new InMemoryBtsDAOImpl();
			dao.incrementHit("B001");
			assertNotNull(dao.selectBts("B001"));
			//assertNull(dao.selectBts("B999"));
		}
}
