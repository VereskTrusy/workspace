package kr.co.sample.board.service;

import java.util.List;

import kr.co.sample.board.exception.WriterAuthenticationException;
import kr.co.sample.board.vo.AttatchVO;
import kr.co.sample.board.vo.BoardVO;
import kr.co.sample.paging.PaginationInfo;

/**
 * 게시판 관리를 위한 Business Logic Object interface 
 */
public interface BoardService {
	/** 
	 * 신규 글 생성
	 * @param board
	 */
	public void createBoard(BoardVO board);
	
	/**
	 * 특정 글 조회
	 * @param boNo
	 */
	public BoardVO readBoard(int boNo);	
	/**
	 * @param paginationInfo
	 * @return
	 */
	public List<BoardVO> readBoardList(PaginationInfo paginationInfo);	
	/**
	 * 작성자 인증
	 * @param inputData
	 * @throws WriterAuthenticationException 인증 실패시 던져질 예외
	 */
	public BoardVO writerAuthenticate(BoardVO inputData) throws WriterAuthenticationException;	
	/**
	 * 게시글 수정
	 * @param board
	 */
	public void updateBoard(BoardVO board);
	/**
	 * 게시글 삭제
	 * @param board
	 * @return
	 */
	public void deleteBoard(BoardVO board);
	/**
	 * 특정 첨부파일 조회
	 * @param atchNo 조회할 첨부파일 번호
	 */
	public AttatchVO readAttatch(int atchNo);

	/**
	 * 첨부파일 삭제
	 * @param atchNo
	 */
	public void deleteAttatch(int atchNo);

}
