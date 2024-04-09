package kr.or.ddit.case1.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.case1.dao.SampleDAO;
import kr.or.ddit.vo.SampleVO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
public class SampleServiceImpl implements SampleService {
//	1. new 키워드를 이용해 의존 객체를 직접 생성하는 방식 : 결합력 최상
//	private SampleDAO dao = new SampleDAOImpl_Maria();
	
//	2. Factory Object[Method] Pattern : factory 객체와 내부에서 생성되는 객체 사이의 결합력 잔존.
//	private SampleDAO dao = SampleDAOFactory.getSampleDAO();
	
//	3. Strategy Pattern(전략 패턴-전략은 언제든지 바뀔수 있다. 고정x)
//		: 전략을 주입받아 사용하는 구조. 전략의 주입자(모든 결합력의 집중)와 주입 방법이 필수. ==> DI container
	
//	4. DI Container 사용 : 의존객체와 객체들 간의 의존관계 형성을 대신 해주는 대상. 주입방법(생성자,setter) 
//	@Autowired
	@Resource(name = "sampleDAOImpl_Maria")
	private SampleDAO dao;
	
	
	
	// 생성자
	public SampleServiceImpl(SampleDAO dao) {
		super();
		this.dao = dao;
		log.info("{} 객체 생성 및 {} 를 생성자로 주입 받음.", 
				this.getClass().getSimpleName(), dao.getClass().getSimpleName());
	}
	
	public void setDao(SampleDAO dao) {
		this.dao = dao;
		log.info("{} 를 기본생성자로 생성, {} 를 setter로 주입 받음",
				this.getClass().getSimpleName(), dao.getClass().getSimpleName());
	}
	
	public void init() {
		log.info("------------주입된DAO : {} ---------------------> {} init 호출"
				, dao.getClass().getSimpleName()
				, this.getClass().getSimpleName());
	}


	@Override
	public List<SampleVO> readSampleList(){
		return dao.selectSampleList();
	}

	@Override
	public SampleVO readSample(String id) {
		SampleVO sample = dao.seleceSample(id);
		if(sample == null) {
			throw new RuntimeException("해당 팀원이 없음");
		}
		return sample;
	}
}
