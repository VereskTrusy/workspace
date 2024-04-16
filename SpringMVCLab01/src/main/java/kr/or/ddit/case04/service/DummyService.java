package kr.or.ddit.case04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.case04.dao.DummyDAO;
import kr.or.ddit.case04.vo.DummyVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DummyService {
	
	@Autowired
	private final DummyDAO dao;
	
	public DummyVO retrieveInfo() {
		DummyVO dummy = dao.selectRawData();
		dummy.setProp(dummy.getProp() + "를 가공한 infomation");
		return dummy;
	}
}
