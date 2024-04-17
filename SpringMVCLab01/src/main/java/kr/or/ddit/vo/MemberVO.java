package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants.Exclude;

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
	@NotBlank
	private String memId;
	@NotBlank
	@Size
	@JsonIgnore
	@Exclude
	private transient String memPass; // json, 직렬화 시 제외한다.
	@NotBlank
	@Size(max = 20)
	private String memName;
	@NotBlank
	@JsonIgnore
	private transient String memRegno1;
	@NotBlank
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
	
	
	// 구매기록
	// Set - 중복허용 x
	@JsonIgnore
	private transient Set<CartVO> cartList; // Has many 관계 
	
	// 사용자 권한 정보
	private String memRole;
}
