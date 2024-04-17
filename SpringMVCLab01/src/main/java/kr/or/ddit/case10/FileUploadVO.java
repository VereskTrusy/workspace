package kr.or.ddit.case10;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadVO {
	private final MultipartFile file;
	
	public FileUploadVO(MultipartFile file) {
		this.file = file;
		this.fileName = file.getOriginalFilename();
		this.fileSize = fileSize.getSize();
		this.saveName = UUID.randomUUID().toString();
	}
	
	public FileUploadVO(String fileName, String saveName, long fileSize) {
		this(null, fileName, saveName, fileSize);
		this.fileName = fileName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	
	private String fileName;
	private String saveName;
	private long fileSize;
	
}
