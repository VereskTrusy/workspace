package kr.co.sample.member.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 회원관리를 위한 Domain Layer
 *  
 *   1번 그룹 (가입시 검증 그룹) : 아이디,  주민번호1, 주민번호2 + 기본 그룹
 *   2번 그룹 (수정시 검증 그룹) : + 기본그룹
 *   기본 그룹 (가입과 수정시 모두 검증 그룹) : 비밀번호, 이름, 우편번호, 주소1, 주소2, 이메일
 *   3번 그룹 (탈퇴시 검증 그룹) : 비밀번호
 *
 */
@Data
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"})
@EqualsAndHashCode(of = "memId")
public class MemberVO implements Serializable{
	private String memId;
	@JsonIgnore
	private transient String memPass;
	private String memName;
	private transient String memRegno1;
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Long memMileage;
	private boolean memDelete;
	
	// 사용자 역할 정보
		private String memRole;
	
	private byte[] memImg;
	private MultipartFile memImage;
	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage.isEmpty()) return;
		this.memImage = memImage;
		this.memImg = memImage.getBytes();
	}
	
	public String getMemImgBase64() {
		if(memImg ==  null) return null;
		return Base64.getEncoder().encodeToString(memImg);
	}
}

















