package kr.or.ddit.Regions.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;
import vo.RegionsVO;

@Slf4j
@SpringJUnitConfig(locations = "file:src/main/resources/kr/or/ddit/spring/conf/*-context.xml")
class RegionsDAOTest {

	@Autowired
	RegionsDAO dao;
	
	@Test
	void test() {
		List<RegionsVO> regions = dao.selectRegionsList();
		regions.forEach((r)->{log.info("레기온 : {}",r);});
	}
	
	@Test
	void test2() {
		String region = "1";
		RegionsVO reg = dao.selectRegion(region);
		log.info("단일조회 : {}", reg);
	}

}