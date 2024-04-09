package kr.or.ddit.el;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations = "classpath:kr/or/ddit/el/conf/El-Context.xml")
class DBInfoTest {
	
	@Resource(name = "DBInfo")
	private DBInfo dbinfo;

	@Test
	void test() {
		log.info("DBInfo : {}", dbinfo);
	}
}
