package kr.or.ddit.case1;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations = "classpath:kr/or/ddit/case1/conf/*-context.xml")
class DataSourceContext {
	@Autowired
	Properties dbInfo;
	
	@Autowired
	DataSource dataSource;
	
	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Test
	void test() {
		log.info("dbInfo : {}", dbInfo);
	}
	
	@Test
	void test2() {
		log.info("dataSource : {}", dataSource);
	}
	
	@Test
	void test3() throws SQLException {
		log.info("connection : {}", dataSource.getConnection());
	}

	@Test
	void test4() {
		log.info("jdbc template : {}", jdbcTemplate);
	}
}
