package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
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
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String prodId; // 상품코드
	@NotBlank
	private String prodName; // 상품명
	@NotBlank(groups = InsertGroup.class)
	private String prodLgu; // 상품분류
	@NotBlank(groups = InsertGroup.class)
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
