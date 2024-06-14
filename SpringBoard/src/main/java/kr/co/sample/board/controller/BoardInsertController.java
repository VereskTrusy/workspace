package kr.co.sample.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.sample.board.service.BoardService;
import kr.co.sample.board.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 게시글 작성 컨트롤러
 *
 */
@Slf4j
@Controller
@RequestMapping("/board/new")
//@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasAuthority('WRITE')")
public class BoardInsertController {
	public static final String MODELNAME = "newBoard";
	
	@Autowired
	private BoardService service;
	
	@ModelAttribute(MODELNAME)
	public BoardVO newBoard() {
		return new BoardVO();
	}
	
	@GetMapping
	public String insertForm() {
		return "board/boardForm";
	}
	
	@PostMapping
	public String insert(
		@Valid @ModelAttribute(MODELNAME) BoardVO newBoard
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, Authentication authentication
	) {
//		newBoard.setBoWriter(authentication.getName());
		if(errors.hasErrors()) {
			redirectAttributes.addFlashAttribute(MODELNAME, newBoard);
			redirectAttributes.addFlashAttribute(
				BindingResult.MODEL_KEY_PREFIX + MODELNAME, errors
			);
			return "redirect:/board/new";
		}else {
			service.createBoard(newBoard);
			return "redirect:/board/"+newBoard.getBoNo();
		}
	}
	
}