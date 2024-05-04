package kr.or.ddit.controller.util;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import kr.or.ddit.mapper.BoardMapper;
import kr.or.ddit.vo.BoardFileVO;

public class FileUploadUtils {

	public static void boardFileUpload(
			List<BoardFileVO> boardFileList,
			int boNo, HttpServletRequest req, BoardMapper mapper) throws Exception {
		String savePath = "/resources/board/";
		
		if(boardFileList != null && boardFileList.size() > 0) {
			for(BoardFileVO boardFileVO : boardFileList) {
				String saveName = UUID.randomUUID().toString();	// UUID의 랜덤 파일명 생성
				saveName = saveName + "_" + boardFileVO.getFileName().replaceAll(" ", "_");
				
				String saveLocate = req.getServletContext().getRealPath(savePath + boNo);
				File file = new File(saveLocate);
				if(!file.exists()) {	// 업로드를 하기 위한 폴더 구조가 존재하지 않을 때
					file.mkdirs();		// 폴더 생성
				}
				
				saveLocate += "/" + saveName;
				boardFileVO.setBoNo(boNo);					// 게시글 번호 설정
				boardFileVO.setFileSavepath(saveLocate);	// 파일 업로드 경로 설정
				mapper.insertBoardFile(boardFileVO);// 공지사항 게시글 파일 데이터 추가
				
				File saveFile = new File(saveLocate);
				boardFileVO.getItem().transferTo(saveFile);	// 파일 복사
			}
		}
	}
	
}
