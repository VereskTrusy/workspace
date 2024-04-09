package kr.or.ddit.case6;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations = "classpath:kr/or/ddit/case6/conf/Case6-Context.xml")
class CollectionDIOuterTest { 
	// 테스트 하려면  CollectionDIOuterTest 이 먼저 빈으로 등록되고
	// 주입을 받아야 사용가능 하지만
	// @Autowired 어노테이션을 통해 빈으로 등록하는 방법을 사용한다.
	
	@Autowired
	CollectionDIOuter outer1; 
	@Resource(name = "outer2") // 안전제일
	CollectionDIOuter outer2; 
	
	@Test
	void test() {
		log.info("outer1 : {}", outer1);
		log.info("outer2 : {}", outer2);
	}

}
