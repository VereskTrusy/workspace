package kr.co.sample.board.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.sample.board.service.BoardService;
import kr.co.sample.board.vo.AttatchVO;
import kr.co.sample.board.vo.BoardVO;
import kr.co.sample.paging.PaginationInfo;
import kr.co.sample.paging.PaginationRenderer;

/**
* 게시글(목록) 조회, 첨부파일 다운로드 컨트롤러
*/
@Controller
@RequestMapping("/board")
public class BoardReadController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private PaginationRenderer renderer;
	
	
	@GetMapping
	public String boardList(
		@ModelAttribute("paginationInfo") PaginationInfo paging
		, Model model
	) {
		List<BoardVO> boardList = service.readBoardList(paging);
		model.addAttribute("boardList", boardList);
		String funcName = "paging";
		model.addAttribute("pagingFunction", funcName);
		model.addAttribute("pagingHTML", renderer.renderPagination(paging, funcName));
		return "board/boardList";
	}
	
	@GetMapping("{boNo}")
	public String boardDetail(@PathVariable int boNo, Model model) {
		BoardVO board = service.readBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardDetail";
	}
	
	@GetMapping("{boNo}/atch/{atchNo}")
	public ResponseEntity<Resource> atchDownload(@PathVariable int atchNo) {
		AttatchVO atch = service.readAttatch(atchNo);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentLength(atch.getAtchFilesize());
		// Content-Disposition: attachment; filename="filename.jpg"
		ContentDisposition contentDisposition = 
			ContentDisposition.attachment()
						.filename(atch.getAtchFilename(), Charset.defaultCharset())
										.build();
		headers.setContentDisposition(contentDisposition);
		return ResponseEntity.ok()
						.headers(headers)
						.body(atch.getBinary());
	}
}