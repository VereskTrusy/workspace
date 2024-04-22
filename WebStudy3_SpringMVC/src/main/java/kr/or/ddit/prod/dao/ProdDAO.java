package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리(CRUD)
 * 
 *
 */
@Mapper
public interface ProdDAO {
	/**
	 * 
	 * @param prod
	 * @return 등록성공여부
	 */
	int insertProd(ProdVO prod);
	
	/**
	 * 상품코드, 거래처코드, 분류코드, 상품명, 구매가, 판매가, 마일리지, 입고일을 조회한다.
	 * @return empty체크 할것
	 */
	List<ProdVO> selectProdList(PaginationInfo paging);
	public int selectTotalRecord(PaginationInfo paging);
	/**
	 * 
	 * @param prodId
	 * @return 단건. 널반환 가능함
	 */
	ProdVO selectProd(String prodId);
	
	/**
	 * 
	 * @param prod
	 * @return 상품수정성공여부
	 */
	int updateProd(ProdVO prod);
	
	/**
	 * 삭제 기능 구현 불가
	 * @return 상품삭제성공여부
	 */
//	int deleteProd(String prodId);
}
