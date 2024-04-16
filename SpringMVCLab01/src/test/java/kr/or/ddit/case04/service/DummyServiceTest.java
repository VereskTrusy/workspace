package kr.or.ddit.case04.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class DummyServiceTest {

	@Autowired
	private DummyService service;
	
	@Test
	void test() {
		service.retrieveInfo();
	}

}
