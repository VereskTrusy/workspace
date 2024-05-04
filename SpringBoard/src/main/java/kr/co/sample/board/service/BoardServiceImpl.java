package kr.co.sample.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.function.Failable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.sample.board.exception.BoardException;
import kr.co.sample.board.exception.WriterAuthenticationException;
import kr.co.sample.board.mapper.AttatchMapper;
import kr.co.sample.board.mapper.BoardMapper;
import kr.co.sample.board.vo.AttatchVO;
import kr.co.sample.board.vo.BoardVO;
import kr.co.sample.paging.PaginationInfo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardMapper boardMapper;
	private final AttatchMapper atchMapper;
	
	@Value("#{appInfo.atchSavePath}")
	private File saveFolder; // AppInfo 설정 및 properties 파읿 빈 등록(root-context.xml)
		
	@Resource(name="passwordEncoder")
	private PasswordEncoder passwordEncoder;//PasswordEncoderFactories 빈 등록(security-context.xml)
	
	@PostConstruct
	public void init() throws IOException {
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}

	/**
	 * 첨부파일 메타데이터와 2진 데이터 분리 저장
	 * @param board
	 */
	private void saveAttatchList(BoardVO board) {
		try {
			List<AttatchVO> atchList = board.getAtchList();
				Optional.ofNullable(atchList)
				.filter(l->!l.isEmpty())
				.ifPresent(pl->{
					atchMapper.insertAttatchList(board);
					pl.forEach(Failable.asConsumer((p)->p.saveToFolder(saveFolder)));
				});
		}catch (Exception e) {
			throw new BoardException(e);
		}
	}

	@Override
	public void createBoard(BoardVO board) {
		board.setBoPasswd(passwordEncoder.encode(board.getBoPasswd()));
		if(boardMapper.insertBoard(board) > 0) {
			saveAttatchList(board);
		}
	}

	@Override
	public BoardVO readBoard(int boNo) {
		BoardVO board = Optional.ofNullable(boardMapper.selectBoard(boNo))
				.orElseThrow(()->new BoardException(String.format("%d 번 글이 없음.", boNo)));
		boardMapper.incrementHit(boNo);
		return board;
	}
	
	@Override
	public List<BoardVO> readBoardList(PaginationInfo paging) {
		paging.setTotalRecord(boardMapper.selectBoardCount(paging));
		List<BoardVO> boardList = boardMapper.selectBoardList(paging);
		return boardList;
	}
	
	@Override
	public BoardVO writerAuthenticate(BoardVO inputData) throws WriterAuthenticationException {
		BoardVO authBoard = readBoard(inputData.getBoNo());
		String rawPassword = inputData.getBoPasswd();
		String encodedPassword = authBoard.getBoPasswd();
		if(passwordEncoder.matches(rawPassword, encodedPassword))  return authBoard;
		else  throw new WriterAuthenticationException("비밀번호 오류, 인증 실패");
	}

	@Override
	public void updateBoard(BoardVO board) {
		writerAuthenticate(board);
		if(boardMapper.updateBoard(board)>0) {			
			saveAttatchList(board);
		}
	}
	@Override
	public void deleteBoard(BoardVO board) {
		BoardVO saved = writerAuthenticate(board);
		if(atchMapper.deleteAttatches(board.getBoNo())>0) {
			saved.getAtchList().stream()
				.forEach(atch->FileUtils.deleteQuietly(
					new File(saveFolder, atch.getAtchSavename())
				));
		}		boardMapper.deleteBoard(saved.getBoNo());
	}

	private AttatchVO validateAttatch(int atchNo) {
		AttatchVO atch = Optional.ofNullable(atchMapper.selectAttatch(atchNo))
				.orElseThrow(()->
				new BoardException(String.format("%d 해당 파일의 메타 정보가 없음.", atchNo)));
		File atchFile = new File(saveFolder, atch.getAtchSavename());
		if(!atchFile.exists()) {
			throw new BoardException(String.format("%s 해당 파일이 없음.", atchFile.getAbsolutePath()));
		}
		atch.setBinary(new FileSystemResource(atchFile));
		return atch;
	}
	
	@Override
	public AttatchVO readAttatch(int atchNo) {
		AttatchVO atch = validateAttatch(atchNo);
		atchMapper.incrementDowncount(atchNo);
		return atch;
		
	}
	
	@Override
	public void deleteAttatch(int atchNo) {
		AttatchVO atch = validateAttatch(atchNo);
		atchMapper.deleteAttatch(atchNo);
		try {
			FileUtils.deleteQuietly(atch.getBinary().getFile());
		}catch (IOException e) {
			throw new BoardException(e);
		}
	}

}       
	