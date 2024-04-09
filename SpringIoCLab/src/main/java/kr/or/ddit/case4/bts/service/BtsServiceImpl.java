package kr.or.ddit.case4.bts.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.case4.bts.dao.BtsDAO;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.BtsVO;
import lombok.RequiredArgsConstructor;

@Service(value = "btsService")
@RequiredArgsConstructor // final만 생성자로 추가
public class BtsServiceImpl implements BtsService {
	
	private final BtsDAO dao;
	

	@Override
	public BtsVO readBts(String code) throws PkNotFoundException {
		BtsVO bts = dao.selectBts(code);
		if(bts == null) {
			throw new PkNotFoundException(404);
		}
		return bts;
	}

	@Override
	public List<BtsVO> readBtsList() {
		return dao.selectBtsList();
	}

	@Override
	public void incrementHit(String code) {
		dao.incrementHit(code);
	}

}
