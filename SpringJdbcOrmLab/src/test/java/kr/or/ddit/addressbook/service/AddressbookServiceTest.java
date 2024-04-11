package kr.or.ddit.addressbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.AbstractModelContextTest;
import kr.or.ddit.vo.AddressbookVO;

@Transactional // AOP 방법에 따른 트랜젝션 관리
class AddressbookServiceTest extends AbstractModelContextTest{
	
	@Autowired
	AddressbookService service;

	@Test
	void testAddressbookList() {
		service.addressbookList();
	}

	@Test
	void testInsertAddressbook() {
		AddressbookVO vo = new AddressbookVO();
		vo.setMemId("a001");
		vo.setAdrsTel("010-1234-1234");
		vo.setAdrsName("김은대");
		vo.setAdrsAdd("대전2");
		service.insertAddressbook(vo);
	}
}
