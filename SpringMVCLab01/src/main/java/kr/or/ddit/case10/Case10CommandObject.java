package kr.or.ddit.case10;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
public class Case10CommandObject implements Serializable{
	
	private String[] upload;
	private int count;
	@ToString.Exclude
	@JsonIgnore
	private transient MultipartFile[] uploadFile;
	private List<FileUploadVO> fileList;
	
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = uploadFile;
		this.fileList = new ArrayList<>();
		for(MultipartFile single :uploadFile) {
			if(single.isEmpty()) {
				continue;
			}
			new FileUploadVO(single);
			fileList.add(new FileUploadVO(null));
		}
	}
}
