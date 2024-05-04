package kr.co.sample.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.sample.board.vo.BoardVO;
import kr.co.sample.paging.PaginationInfo;

/**
 * 게시판 관리를 위한 DataAccessObject interface, Persistence Layer
 */
@Mapper
public interface BoardMapper {
	/**
	 * 새글 등록
	 * 
	 * @param board
	 * @return
	 */
	public Integer insertBoard(BoardVO board);

	/**
	 * 특정 글 조회
	 * 
	 * @param boNo 조회할 글 번호
	 * @return
	 */
	public BoardVO selectBoard(int boNo);

	/**
	 * 글 조회수 카운트
	 * 
	 * @param boNo
	 * @return
	 */
	public int incrementHit(int boNo);

	/**
	 * 글 목록 조회
	 * 
	 * @param paginationInfo
	 * @return
	 */
	public List<BoardVO> selectBoardList(PaginationInfo paging);

	/**
	 * 게시글 목록 수 조회
	 * 
	 * @param paginationInfo
	 * @return
	 */
	public int selectBoardCount(PaginationInfo paging);

	/**
	 * 글 수정
	 * 
	 * @param board
	 * @return
	 */
	public int updateBoard(BoardVO board);

	/**
	 * 글 삭제
	 * 
	 * @param boNo
	 * @return
	 */
	public int deleteBoard(int boNo);
}
