package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

public interface OthersService {
	public List<LprodVO> retriveLprodList();
	public List<BuyerVO> retriveBuyerList();
}
