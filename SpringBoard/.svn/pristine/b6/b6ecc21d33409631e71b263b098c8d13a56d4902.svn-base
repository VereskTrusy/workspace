package kr.co.sample.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.sample.board.exception.BoardException;
import kr.co.sample.board.service.BoardService;
import kr.co.sample.board.vo.BoardVO;

/**
 * 게시글 삭제와 첨부파일 삭제 컨트롤러
 */
@Controller
@RequestMapping("/board/{boNo}")
public class BoardDeleteController {
	@Autowired
	private BoardService service;
	@DeleteMapping("atch/{atchNo}")
	@ResponseBody
	public String deleteAttatch(@PathVariable int atchNo) {
		service.deleteAttatch(atchNo);
		return "SUCCESS";
	}
	@DeleteMapping
	public String delete(BoardVO board, RedirectAttributes redirectAttributes) {
		try {
			service.deleteBoard(board);
			return "redirect:/board";
		}catch (BoardException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/board/{boNo}";
		}
	}
}
