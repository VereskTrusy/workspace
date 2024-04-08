package kr.or.ddit.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SampleVOTest {

	@Test
	void test() {
		SampleVO vo1 = SampleVO.builder().build();
		log.info("vo1 : {}" , vo1);
		
		SampleVO vo2 = SampleVO.builder()
						.id("a001")
						.build();
		log.info("vo2 : {}", vo2);
		
		SampleVO vo3 = SampleVO.builder()
				.id("a001")
				.name("김은대")
				.build();
		log.info("vo3 : {}", vo3);
		
		SampleVO vo4 = SampleVO.builder()
				.id("a001")
				.name("김은대")
				.role("ROLE_ADHD")
				.build();
		log.info("vo4 : {}", vo4);
	}

}
