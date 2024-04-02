package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;

public interface BuyerDAO {
//	insert
	
	/**
	 * 거래처아이디, 거래처명, 분류코드(분류명), 소재지, 담당자명, 전화번호
	 * 디비 관계설정시 > 분류코드에 해당하는 분류명은 LPROD 테이블에 정보가 있으니까
	 * VO에 has a 관계 설정해주고
	 * mapper 에 해당하는 관계 설정해주면된다.
	 * 
	 * @return
	 */
	public List<BuyerVO> selectBuyerList();
	public BuyerVO selectBuyer(String buyerId);
//	update
//	delete // 못지움
}
