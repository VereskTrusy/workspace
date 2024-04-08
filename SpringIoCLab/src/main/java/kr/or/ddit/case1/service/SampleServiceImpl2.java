package kr.or.ddit.case1.service;

import java.util.List;

import kr.or.ddit.case1.dao.SampleDAO;
import kr.or.ddit.vo.SampleVO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SampleServiceImpl2 implements SampleService{
	private SampleDAO dao;
	
	// setter 사용
	public void setDao(SampleDAO dao) {
		this.dao = dao;
	}
	
	public List<SampleVO> readSampleList(){
		return dao.selectSampleList();
	}

	@Override
	public SampleVO readSample(String id) {
		
		return dao.seleceSample(id);
	}
}
