package kr.or.ddit.case4.bts.service;

import java.util.List;

import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.BtsVO;

/**
 * Business Logic Layer
 *
 */
public interface BtsService {
	
	/**
	 * 한명의 멤버 조회
	 * @param code
	 * @return 존재하지 않는 경우, {@link PkNotFoundException} 발생
	 */
	public BtsVO readBts(String code) throws PkNotFoundException; 
	/**
	 * 전체조회
	 * @return
	 */
	public List<BtsVO> readBtsList();
	
	/**
	 * 한사람의 멤버가 조회될 때 조회수를 증가시킴.
	 * @param code
	 */
	public void incrementHit(String code);
}
