package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;

/**
 * 거래처의 등록, 수정, 목록조회, 단건조회
 * 
 */
public interface BuyerService {
	public List<BuyerVO> selectBuyerList();
	public BuyerVO selectBuyer(String buyerId);
}
