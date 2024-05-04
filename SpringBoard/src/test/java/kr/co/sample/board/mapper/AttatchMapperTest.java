package kr.co.sample.board.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sample.AbstractRootContextTest;
import kr.co.sample.board.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class AttatchMapperTest extends AbstractRootContextTest {
	@Autowired
	AttatchMapper atchMapper;

	@Test
	void testInsertAttatchList() {
		BoardVO board = new BoardVO();
		board.setBoNo(320);
		MockMultipartFile mockFile1 = new MockMultipartFile("uploadFile", "원본파일명1", "image/jpeg",
				new byte[] { 1, 2, 3 });
		MockMultipartFile mockFile2 = new MockMultipartFile("uploadFile", "원본파일명2", "image/jpeg",
				new byte[] { 1, 2, 3 });
		board.setBoFiles(new MultipartFile[] { mockFile1, mockFile2 });
		board.getAtchList().forEach(af->log.info("파일 : {}", af));
		assertDoesNotThrow(() -> atchMapper.insertAttatchList(board));
	}

	@Test
	void testSelectAttatch() {
		assertDoesNotThrow(() -> atchMapper.selectAttatch(3));
	}

	@Test
	void testIncrementDowncount() {
		assertDoesNotThrow(() -> atchMapper.incrementDowncount(3));
	}

	@Test
	void testDeleteAttatch() {
		assertDoesNotThrow(() -> atchMapper.deleteAttatch(3));
	}

	@Test
	void testDeleteAttatches() {
		assertDoesNotThrow(() -> atchMapper.deleteAttatches(30));
	}
}