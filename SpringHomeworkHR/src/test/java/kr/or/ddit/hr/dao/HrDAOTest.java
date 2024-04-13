package kr.or.ddit.hr.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;
import vo.RegionsVO;

@Slf4j
@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class HrDAOTest {
	
	@Autowired
	HrDAO dao;

	@Test
	void test() {
		List<RegionsVO> allLocationList = dao.selectAllLocationInfo();
		allLocationList.forEach((a)->{log.info("{}", a);});
	}

}
