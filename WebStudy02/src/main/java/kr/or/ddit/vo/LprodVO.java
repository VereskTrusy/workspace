package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="lprodId")
public class LprodVO implements Serializable{
	private Long lprodId; // 상품순번
	private String lprodGu; // 상품분류코드
	private String lprodNm; // 상품분류명
}
