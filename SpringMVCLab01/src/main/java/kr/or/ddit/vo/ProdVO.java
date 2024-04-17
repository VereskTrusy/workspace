package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

/**
 * select 
 * insert : 기본그룹
 * update : 상품코드 + 기본그룹
 * delete(안대앵) : 상품코드
 * 기본그룹 : 상품명, 상품분류, 거래처, 구매가, 판매가, 세일가, 요약정보, 이미지, 총재고, 적정재고
 */
@Data
@EqualsAndHashCode(of="prodId")
//@ToString(exclude = "prodDetail")
public class ProdVO implements Serializable {
	@NotBlank
	private String prodId; // 상품코드
	@NotBlank
	private String prodName; // 상품명
	@NotBlank
	private String prodLgu; // 상품분류
	@NotBlank
	private String prodBuyer; // 거래처
	@Min(value = 0)
	private long prodCost; // 구매가
	@Min(value = 0)
	private long prodPrice; // 판매가
	@Min(value = 0)
	private long prodSale; // 세일가
	@NotBlank
	private String prodOutline; // 요약정보
	@Exclude
	@JsonIgnore
	private String prodDetail; // 상세정보
	@NotBlank
	private String prodImg; // 이미지
	@Min(value = 0)
	private long prodTotalstock; // 총재고
	@DateTimeFormat(iso = ISO.DATE) // 문자열 파싱 해주는 설정. java.util.Date, java.util.Calender, java.time.*
	private LocalDate prodInsdate; // 입고일
	@Min(value = 0)
	private long prodProperstock; // 적정재고
	private String prodSize; // 크기
	private String prodColor; // 색상
	private String prodDelivery; // 배송방법
	private String prodUnit; // 단위
	private Long prodQtyin; // 입고량
	private Long prodQtysale; // 출고량
	private Long prodMileage; // 마일리지
	
	
	private BuyerVO buyer; // Has a 관계(1:1), PROD(1) : BUYER(1) --> ProdVO has a BuyerVO
	private LprodVO lprod; // Has a 관계(1:1), PROD(1) : LPROD(1) --> ProdVO has a LprodVO
	
	private List<CartVO> cartList; // has many 관계
}
