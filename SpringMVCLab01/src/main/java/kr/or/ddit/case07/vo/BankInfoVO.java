package kr.or.ddit.case07.vo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class BankInfoVO {
	@NotBlank
	private String bankNo;
	@NotBlank
	private String bankName;
	
	@NotBlank String bankUserName;
//	private LocalDate bankDate;
//	@ NotBlank // 파라미터로 처리하기위해서 String 타입에만 사용할 수 있는 NotBlank 뺌...
	@DateTimeFormat(iso = ISO.DATE) // 데이터가 아닌 문자가 전송됐을때 해당 어노테이션이 필요하다. > 파싱 처리를 위해
	private LocalDate bankDate;
}
