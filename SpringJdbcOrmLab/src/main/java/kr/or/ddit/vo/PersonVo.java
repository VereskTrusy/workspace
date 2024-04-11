package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * MemberData.properties 파일의 저장된 사용자의 정보 로딩에 필요한 객체
 * model : id, name, gender, age, address
 * 
 * Domain Layer : raw data 구조를 반영하여 모델링함.
 */
@Data
public class PersonVo {

	public PersonVo() {
		super();
	}

	
	public PersonVo(String id, String name, String gender, String age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
	}

	@NotBlank
	private String id;
	private String name;
	private String gender;
	private String age;
	private String address;
	
	
}
