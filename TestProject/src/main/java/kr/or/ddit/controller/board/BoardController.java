package kr.or.ddit.controller.board;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.util.MediaUtils;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardFileVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Inject
	private IBoardService service;
	
	@Resource(name="uploadPath")
	private String resourcePath; 
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER,ADMIN 역할만 접근 가능)
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String boardList(
		@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
		@RequestParam(required = false, defaultValue = "title") String searchType,
		@RequestParam(required = false) String searchWord,
		Model model) {
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		// 검색이 이뤄지면 아래가 실행됨
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);	// startRow, endRow, startPage, endPage가 결정
		int totalRecord = service.selectBoardCount(pagingVO);	// 총 게시글 수
		
		pagingVO.setTotalRecord(totalRecord);	// totalPage 결정
		List<BoardVO> dataList = service.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/list";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER 역할만 접근 가능)
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String boardDetail(int boNo, Model model) {
		BoardVO boardVO = service.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		return "board/detail";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER 역할만 접근 가능)
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String boardForm() {
		return "board/form";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER 역할만 접근 가능)
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String boardInsert(
			HttpServletRequest req,
			HttpSession session,
			RedirectAttributes ra,
			BoardVO boardVO, Model model) throws Exception {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(boardVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요.");
		}
		if(StringUtils.isBlank(boardVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요.");
		}
		
		if(errors.size() > 0) {	// 에러 발생	
			model.addAttribute("errors", errors);
			model.addAttribute("boardVO", boardVO);
			goPage = "board/form";
		}else {
			// 일반 세션 활용
//			MemberVO memberVO = (MemberVO) session.getAttribute("SessionInfo");
			
			// 시큐리티 활용
			CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			MemberVO memberVO = user.getMember();
			
			if(memberVO != null) {
				// 로그인 후 등록된 세션 정보(로그인 한 회원 정보가 들어있음)
				// 회원 정보 중, id를 작성자로 셋팅
				boardVO.setBoWriter(memberVO.getMemId());		
				ServiceResult result = service.insertBoard(req, boardVO);
				
				if(result.equals(ServiceResult.OK)) {
					goPage = "redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
				}else {
					model.addAttribute("message", "서버에러, 다시 시도해주세요!");
					goPage = "board/form";
				}
			}else {
				ra.addFlashAttribute("message", "로그인 후에 사용 가능합니다!");
				goPage = "redirect:/signin.do";
			}
		}
		return goPage;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER 역할만 접근 가능)
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String boardDelete(HttpServletRequest req,
			RedirectAttributes ra,
			int boNo, Model model) {
		String goPage = "";
		ServiceResult result = service.deleteBoard(boNo);
		if(result.equals(ServiceResult.OK)) {
			ra.addFlashAttribute("message", "삭제가 완료되었습니다!");
			goPage = "redirect:/board/list.do";
		}else {
			ra.addFlashAttribute("message", "서버오류, 다시 시도해주세요!");
			goPage = "redirect:/board/detail.do?boNo="+boNo;
		}
		
		return goPage;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER 역할만 접근 가능)
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String boardUpdateForm(int boNo, Model model) {
		BoardVO boardVO = service.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		model.addAttribute("status", "u");
		return "board/form";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER 역할만 접근 가능)
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String noticeModify(
			HttpServletRequest req,
			RedirectAttributes ra,
			BoardVO boardVO, Model model) throws Exception {
		String goPage = "";
		ServiceResult result = service.updateBoard(req, boardVO);
		if(result.equals(ServiceResult.OK)) {
			ra.addFlashAttribute("message", "수정이 완료되었습니다!");
			goPage = "redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
		}else {
			model.addAttribute("message", "수정에 실패하였습니다!");
			model.addAttribute("board", boardVO);
			model.addAttribute("status", "u");
			goPage = "board/form";
		}
		
		return goPage;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")	// 스프링 시큐리티 적용(ROLE_MEMBER 역할만 접근 가능)
	@RequestMapping(value="/download.do", method = RequestMethod.GET)
	public ResponseEntity<byte[]> fileDownload(int fileNo) throws IOException{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String fileName = null;
		BoardFileVO fileVO = service.selectFileInfo(fileNo);
		if(fileVO != null) {
			fileName = fileVO.getFileName();
			try {
				String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
				MediaType mType = MediaUtils.getMediaType(formatName);
				HttpHeaders headers = new HttpHeaders();
				in = new FileInputStream(fileVO.getFileSavepath());
				
//				if(mType != null) {
//					headers.setContentType(mType);
//				}else {
					fileName = fileName.substring(fileName.indexOf("_") + 1);
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					headers.add("Content-Disposition", "attachment; filename=\"" + 
							new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
//				}
				entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			}catch(Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			}finally {
				in.close();
			}
		}else {
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
