package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원관리를 위한 Domain Layer
 * 
 * lombok
 * 자바빈규약을 자동으로 지켜줌
 * 멤버변수 변경시 변경사항 자동으로 변경
 * vo의 구조 파악 용이
 * 
 * 자바빈 규약 6가지를 지키는게 가장 중요하다.
 * 1. getter
 * 2. setter
 */
@Data
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"}) // 빼는게 중요
@EqualsAndHashCode(of = "memId") // 넣는게 중요
public class MemberVO implements Serializable{
	private String memId;
	@JsonIgnore
	private transient String memPass; // json, 직렬화 시 제외한다.
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
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
	private boolean memDelete; // 값이 null이면 false 값이 있다면 true로 마이바티스가 변환해준다.
	
	
}
