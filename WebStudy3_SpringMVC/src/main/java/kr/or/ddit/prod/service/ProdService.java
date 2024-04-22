package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

public interface ProdService {
	
	/**
	 * 
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult createProd(ProdVO prod);
	
	
	public List<ProdVO> retriveProdList(PaginationInfo paging);
	
	
	public ProdVO retriveProd(String prodId) throws PkNotFoundException;
	
	/**
	 * 
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult modifyProd(ProdVO prod);
	
}
