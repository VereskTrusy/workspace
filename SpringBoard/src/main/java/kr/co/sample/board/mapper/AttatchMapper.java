package kr.co.sample.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.sample.board.vo.AttatchVO;
import kr.co.sample.board.vo.BoardVO;

/**
 * 첨부파일의 메타데이터를 위한 CRUD interface
 */
@Mapper
public interface AttatchMapper {
	/**
	* 첨부파일 저장을 위한 메소드, 파라미터 맵을 형성하기 위해 @Param 사용함.
	*/
	public int insertAttatchList(@Param("board") BoardVO board);

	/**
	 * 첨부파일 조회를 위한 메소드
	 */
	public AttatchVO selectAttatch(int atchNo);

	/**
	 * downcount 증가를 위한 메소드
	 */
	public int incrementDowncount(int atchNo);

	/**
	 * 첨부파일 삭제를 위한 메소드
	 */
	public int deleteAttatch(@Param("atchNo") int atchNo);

	/**
	 * 특정 게시글의 첨부파일 전체 삭제
	 */
	public int deleteAttatches(@Param("boNo") int boNo);
}














