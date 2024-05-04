package kr.co.sample.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.sample.board.exception.BoardException;
import kr.co.sample.board.service.BoardService;
import kr.co.sample.board.vo.BoardVO;

/**
* 게시글 수정 및 작성자 인증 컨트롤러
*/
@Controller
@RequestMapping("/board/{boNo}")
public class BoardUpdateController {
	public static final String MODELNAME = "targetBoard";	
	@Autowired
	private BoardService service;	
	@GetMapping("auth")
	public String authenticateForm() {
		return "board/authForm";
	}
	
	@PostMapping("auth")
	public String authenticateWriter(
		BoardVO inputBoard
		, RedirectAttributes redirectAttributes
	) {
		try {
			BoardVO authenticatedBoard = service.writerAuthenticate(inputBoard);
			redirectAttributes.addFlashAttribute(MODELNAME, authenticatedBoard);
			return "redirect:/board/{boNo}/edit";
		}catch (BoardException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/board/{boNo}/auth";
		}
	}
	
	@GetMapping("edit")
	public String updateForm(@PathVariable int boNo, Model model) {
		BoardVO targetBoard = (BoardVO) model.asMap().get(MODELNAME);
		if(targetBoard!=null && boNo == targetBoard.getBoNo()) {
			return "board/boardEdit";
		}else {
			return "redirect:/board/{boNo}/auth";
		}
	}	
	
	@PutMapping("edit")
	public String update(
		@Valid @ModelAttribute(MODELNAME) BoardVO targetBoard
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		if(errors.hasErrors()) {
			redirectAttributes.addFlashAttribute(MODELNAME, targetBoard);
			redirectAttributes.addFlashAttribute(
				BindingResult.MODEL_KEY_PREFIX + MODELNAME, errors
			);
			return "redirect:/board/{boNo}/edit";
		}else {
			try {
				service.updateBoard(targetBoard);
				return "redirect:/board/"+targetBoard.getBoNo();
			}catch (BoardException e) {
				redirectAttributes.addFlashAttribute(MODELNAME, targetBoard);
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				return "redirect:/board/{boNo}/edit";
			}
		}
	}
}
