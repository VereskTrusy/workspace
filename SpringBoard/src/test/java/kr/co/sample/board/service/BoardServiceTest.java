package kr.co.sample.board.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sample.AbstractRootContextTest;
import kr.co.sample.board.exception.BoardException;
import kr.co.sample.board.exception.WriterAuthenticationException;
import kr.co.sample.board.vo.BoardVO;
import kr.co.sample.paging.PaginationInfo;
import lombok.extern.slf4j.Slf4j;
@Slf4j
class BoardServiceTest extends AbstractRootContextTest{
	@Autowired
	BoardService service;
	
	@Test
	void testCreateBoard() {
		BoardVO board = new BoardVO();
		board.setBoTitle("신규 테스트 제목");
		board.setBoWriter("작성자");
		board.setBoPasswd("java");
		board.setBoEmail("aa@naver.com");
		board.setBoIp("111.111.111.111");
		board.setBoContent("테스트 게시글 내용");
		MockMultipartFile file1 = 
				new MockMultipartFile("boFiles", "origin1", "image/jpeg", new byte[] {1,2,3});
		MockMultipartFile file2 = 
				new MockMultipartFile("boFiles", "origin2", "image/jpeg", new byte[] {1,2,3});
		board.setBoFiles(new MultipartFile[] {file1, file2});
		assertDoesNotThrow(()->service.createBoard(board));
	}

	@Test
	void testReadBoard() {
		BoardVO board = assertDoesNotThrow(()->service.readBoard(300));
		board.getAtchList().forEach(af->log.info("첨부파일 2진 데이터 : {}",  af.getBinary()));
	}

	@Test
	void testReadBoardList() {
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(1);
		assertDoesNotThrow(()->service.readBoardList(paging));
		log.info("pagination info : {}", paging);
	}

	@Test
	void testWriterAuthenticate() {
		BoardVO board = new BoardVO();
		board.setBoNo(300);
		board.setBoPasswd("java");
		assertDoesNotThrow(()->service.writerAuthenticate(board));
		board.setBoPasswd("invalid");
		assertThrows(WriterAuthenticationException.class, ()->service.updateBoard(board));
	}

	@Test
	void testUpdateBoard() {
		BoardVO board = new BoardVO();
		board.setBoNo(300);
		board.setBoPasswd("java");
		board.setBoEmail("aa@naver.com");
		board.setBoContent("테스트 게시글 내용");
		MockMultipartFile file1 = 
				new MockMultipartFile("boFiles", "origin1", "image/jpeg", new byte[] {1,2,3});
		MockMultipartFile file2 = 
				new MockMultipartFile("boFiles", "origin2", "image/jpeg", new byte[] {1,2,3});
		board.setBoFiles(new MultipartFile[] {file1, file2});
		assertDoesNotThrow(()->service.updateBoard(board));
		board.setBoPasswd("invalid");
		assertThrows(WriterAuthenticationException.class, ()->service.updateBoard(board));
	}

	@Test
	void testDeleteBoard() {
		BoardVO board = new BoardVO();
		board.setBoNo(320);
		board.setBoPasswd("java");
		assertDoesNotThrow(()->service.deleteBoard(board));
		board.setBoPasswd("invalid");
		assertThrows(WriterAuthenticationException.class, ()->service.deleteBoard(board));
	}

	@Test
	void testReadAttatch() {
		assertThrows(BoardException.class, ()->service.readAttatch(3));
	}

	@Test
	void testDeleteAttatch() {
		assertThrows(BoardException.class, ()->service.deleteAttatch(3));
	}

}
