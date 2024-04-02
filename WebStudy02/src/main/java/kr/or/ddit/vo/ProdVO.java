package kr.or.ddit.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of="prodId")
//@ToString(exclude = "prodDetail")
public class ProdVO {
	private String prodId; // 상품코드
	private String prodName; // 상품명
	private String prodLgu; // 상품분류
	private String prodBuyer; // 거래처
	private Long prodCost; // 구매가
	private Long prodPrice; // 판매가
	private Long prodSale; // 세일가
	private String prodOutline; // 요약정보
	@Exclude
	@JsonIgnore
	private String prodDetail; // 상세정보
	private String prodImg; // 이미지
	private Long prodTotalstock; // 총재고
	private LocalDate prodInsdate; // 입고일
	private Long prodProperstock; // 적정재고
	private String prodSize; // 크기
	private String prodColor; // 색상
	private String prodDelivery; // 배송방법
	private String prodUnit; // 단위
	private Long prodQtyin; // 입고량
	private Long prodQtysale; // 출고량
	private Long prodMileage; // 마일리지
	
	
	private BuyerVO buyer; // Has a 관계(1:1), PROD(1) : BUYER(1) --> ProdVO has a BuyerVO
	private LprodVO lprod; // Has a 관계(1:1), PROD(1) : LPROD(1) --> ProdVO has a LprodVO
}
