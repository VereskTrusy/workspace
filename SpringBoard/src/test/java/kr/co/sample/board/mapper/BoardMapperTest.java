package kr.co.sample.board.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import kr.co.sample.AbstractRootContextTest;
import kr.co.sample.board.vo.BoardVO;
import kr.co.sample.paging.PaginationInfo;
import kr.co.sample.paging.SimpleCondition;

class BoardMapperTest extends AbstractRootContextTest {
	@Autowired
	BoardMapper boardMapper;

	@Test
	void testInsertBoard() {
		BoardVO board = new BoardVO();
		board.setBoTitle("신규 테스트 제목");
		board.setBoWriter("작성자");
		board.setBoPasswd("java");
		board.setBoEmail("aa@naver.com");
		board.setBoIp("111.111.111.111");
		board.setBoContent("테스트 게시글 내용");
		assertDoesNotThrow(() -> boardMapper.insertBoard(board));
	}

	@Test
	void testSelectBoard() {
		assertDoesNotThrow(() -> boardMapper.selectBoard(300));
	}

	@Test
	void testIncrementHit() {
		assertDoesNotThrow(() -> boardMapper.incrementHit(300));
	}

	@Test
	void testSelectBoardList() {
		PaginationInfo paging = new PaginationInfo();
		SimpleCondition simpleCondition = new SimpleCondition();
		simpleCondition.setSearchType("title");
		simpleCondition.setSearchWord("25");
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(1);
		assertDoesNotThrow(() -> boardMapper.selectBoardList(paging));
	}

	@Test
	void testSelectBoardCount() {
		PaginationInfo paging = new PaginationInfo();
		SimpleCondition simpleCondition = new SimpleCondition();
		simpleCondition.setSearchType("title");
		simpleCondition.setSearchWord("25");
		paging.setSimpleCondition(simpleCondition);
		assertDoesNotThrow(() -> boardMapper.selectBoardCount(paging));
	}

	@Test
	void testUpdateBoard() {
		BoardVO board = new BoardVO();
		board.setBoNo(300);
		board.setBoEmail("aa@naver.com");
		board.setBoContent("테스트 게시글 내용 수정수정");
		assertDoesNotThrow(() -> boardMapper.updateBoard(board));
	}

	@Test
	void testDeleteBoard() {
		assertThrows(DataAccessException.class, () -> boardMapper.deleteBoard(340));
	}
}