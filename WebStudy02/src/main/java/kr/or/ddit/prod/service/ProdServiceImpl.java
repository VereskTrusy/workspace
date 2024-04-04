package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	private ProdDAO dao = new ProdDAOImpl();

	@Override
	public List<ProdVO> retriveProdList() {
		List<ProdVO> list = dao.selectProdList();
		return list;
	}

	@Override
	public ProdVO retriveProd(String prodId) throws PkNotFoundException {
		ProdVO item = dao.selectProd(prodId);
		if(item == null) throw new PkNotFoundException(400);
			
		return item;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		if(rowcnt != -1) return ServiceResult.OK;
		else return ServiceResult.FAIL;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		int rowcnt = dao.updateProd(prod);
		if(rowcnt != -1) return ServiceResult.OK;
		else return ServiceResult.FAIL;
	}
	
	
}
