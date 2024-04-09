package kr.or.ddit.case1.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SampleDAOImpl_Maria implements SampleDAO {
	private Map<String, SampleVO> mariaDB;
	{
		mariaDB = new LinkedHashMap<String, SampleVO>();
		mariaDB.put("T001", SampleVO.builder()
				.id("T001")
				.name("정성윤_Maria")
				.role("PL")
				.build());
		mariaDB.put("T002", SampleVO.builder()
				.id("T002")
				.name("장민우_Maria")
				.role("TA")
				.build());
		mariaDB.put("T003", SampleVO.builder()
				.id("T003")
				.name("신의정_Maria")
				.role("UA")
				.build());
		mariaDB.put("T004", SampleVO.builder()
				.id("T004")
				.name("박승준_Maria")
				.role("AA")
				.build());
	}
	
	public void	initDao() {
		log.info("lifecycle callback init 호울");
	}
	
	public void distroy() {
		log.info("lifecycle callback destroy 호울");
	}
	

	public SampleDAOImpl_Maria() {
		super();
		log.info("{} 객체 생성", this.getClass().getSimpleName());
	}

	@Override
	public List<SampleVO> selectSampleList() {
		return new ArrayList<SampleVO>(mariaDB.values());
	}

	@Override
	public SampleVO seleceSample(String id) {
		
		return mariaDB.get(id);
	}

}
