package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants.Exclude;
import oracle.jdbc.logging.annotations.DefaultLevel;

/**
 * 회원관리를 위한 Domain Layer
 * 
 * lombok
 * 자바빈규약을 자동으로 지켜줌
 * 멤버변수 변경시 변경사항 자동으로 변경
 * vo의 구조 파악 용이
 * 
 * 자바빈 규약 6가지를 지키는게 가장 중요하다.
 * 
 * 1번 그룹 (회원 가입 시 검증 할 그룹) : 아이디, 주민번호1, 주민번호2 + 기본그룹
 * 2번 그룹 (회원 정보 수정 시 검증) : + 기본그룹
 * 기본 그룹 (가입과 수정 시 모두 검증 그룹) : 비밀번호, 이름, 우편번호, 주소1, 주소2, 이메일
 * 3번 그룹 (탈퇴시 검증 그룹) : 비밀번호
 */
@Data
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"}) // 빼는게 중요
@EqualsAndHashCode(of = "memId") // 넣는게 중요
public class MemberVO implements Serializable{
	private int rnum;
	
	@NotBlank(groups = InsertGroup.class)
	private String memId;
	@NotBlank(groups = {DeleteGroup.class, Default.class})
	@Size(min = 4, max = 12, groups = DeleteGroup.class)
	@JsonIgnore
	@Exclude
	private transient String memPass; // json, 직렬화 시 제외한다.
	@NotBlank
	@Size(max = 20)
	private String memName;
	@NotBlank(groups = InsertGroup.class, message = "주민번호 1번 누락되었습니다.") // 그룹힌트, 메시지 커스텀.
	@JsonIgnore
	private transient String memRegno1;
	@NotBlank(groups = InsertGroup.class)
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	@NotBlank
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Long memMileage;
	private boolean memDelete; // 값이 null이면 false 값이 있다면 true로 마이바티스가 변환해준다.
	
	// 이미지 컬럼 추가 (MEM_IMG)
	private byte[] memImg;
//	public void setMemImg(MultipartFile memImage) {
//		try {
//			this.memImg = memImage.getBytes();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	private MultipartFile memImage;
	public void setMemImage(MultipartFile memImage) {
		if(memImage.isEmpty()) return;
		this.memImage = memImage;
		try {
			this.memImg = memImage.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 구매기록
	// Set - 중복허용 x
	@JsonIgnore
	private transient Set<CartVO> cartList; // Has many 관계 
	
	// 사용자 권한 정보
	private String memRole;
	
	public String getMemImgBase64() {
		if(memImg == null) return null;
		return Base64.getEncoder().encodeToString(memImg);
	}
}
