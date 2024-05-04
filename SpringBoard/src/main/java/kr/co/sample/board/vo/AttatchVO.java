package kr.co.sample.board.vo;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.function.Failable;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "atchNo")
public class AttatchVO implements Serializable {
	public AttatchVO(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
		atchFilename = uploadFile.getOriginalFilename();
		atchFilesize = uploadFile.getSize();
		atchMime = uploadFile.getContentType();
		atchFancysize = FileUtils.byteCountToDisplaySize(atchFilesize);
		atchSavename = UUID.randomUUID().toString();
	}

	@ToString.Exclude
	private MultipartFile uploadFile; // 업로드된 파일
	private Integer atchNo; // 첨부파일번호(PK)
	private Integer atchRefKey; // 첨부게시물번호
	private String atchSavename; // 서버에 저장시 사용된 파일명(경로포함)
	private String atchFilename; // 사용자가 선택했던 원래 파일명
	private String atchMime; // 파일 타입
	private long atchFilesize; // 파일 크기
	private String atchFancysize; // 파일크기 축약형(뷰)
	private long atchDowncount; // 다운 횟수
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime atchDate; // 업로드 날짜

	public void saveToFolder(File folder) {
		Optional.ofNullable(uploadFile).filter(uf -> !uf.isEmpty())
				.ifPresent(Failable.asConsumer(uf -> uploadFile.transferTo(new File(folder, atchSavename))));
	}

	public byte[] getBytes() {
		return Optional.ofNullable(uploadFile).filter(uf -> !uf.isEmpty())
				.map(Failable.asFunction(MultipartFile::getBytes)).orElse(new byte[] {});
	}

	@ToString.Exclude
	private Resource binary; // 파일 2진데이터
}



















