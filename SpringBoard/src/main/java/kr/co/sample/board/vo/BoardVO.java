package kr.co.sample.board.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "boNo")
public class BoardVO implements Serializable {
	private int rnum;
	private Integer boNo; // 글번호(PK)
	private String boTitle; // 제목
	private String boWriter; // 글쓴이
	private String boPasswd; // 비번
	private String boEmail; // 이메일
	private String boIp; // 글쓴이 IP
	private String boContent; // 내용
	private Integer boHit; // 조회수
	private LocalDateTime boDate; // 최초 등록일
	private int atchSize; // 첨부파일 개수 조회시 사용
	private MultipartFile[] boFiles;
	private List<AttatchVO> atchList;

	/**
	 * 업로드된 첨부파일을 adapter 인 AttatchVO 로 wrapping
	 */
	public void setBoFiles(MultipartFile[] boFiles) {
		this.atchList = Optional.ofNullable(boFiles).map(
				bfs -> Arrays.stream(bfs).filter(bf -> !bf.isEmpty()).map(AttatchVO::new).collect(Collectors.toList()))
				.orElse(Collections.emptyList());
	}
}
