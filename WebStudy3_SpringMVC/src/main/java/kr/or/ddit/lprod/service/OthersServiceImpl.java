package kr.or.ddit.lprod.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.LprodDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OthersServiceImpl implements OthersService {

	@Autowired
	   private final LprodDAO dao;

	   @Override
	   public List<LprodVO> retriveLprodList() {
	      return dao.selectLprodList();
	   }

	   @Override
	   public List<BuyerVO> retriveBuyerList() {
	      List<LprodVO> lprodList = retriveLprodList();
	      List<BuyerVO> buyerList = new ArrayList<BuyerVO>();
	      for(LprodVO lprod : lprodList) {
	         buyerList.addAll(lprod.getBuyerList());
	      }
	      return buyerList;
	   }
}

















