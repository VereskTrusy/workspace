package kr.or.ddit.lprod.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.lprod.dao.LprodDAO;
import kr.or.ddit.lprod.dao.LprodDAOImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

public class OthersServiceImpl implements OthersService {
	private LprodDAO dao = new LprodDAOImpl();

	@Override
	public List<LprodVO> retriveLprodList() {
		return dao.selectLprodList();
	}

	@Override
	public List<BuyerVO> retriveBuyerList() {
		List<LprodVO> lprodVO = retriveLprodList();
		List<BuyerVO> buyerList = new ArrayList<BuyerVO>();
		for(LprodVO lprod : lprodVO) {
			buyerList.addAll(lprod.getBuyerList());
		}
		return buyerList;
	}

}
