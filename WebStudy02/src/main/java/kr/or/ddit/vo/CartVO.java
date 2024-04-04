package kr.or.ddit.vo;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"cartNo", "cartProd"})
public class CartVO {
	private String cartMember; // 회원ID
	private String cartNo; // 주문번호
	private String cartProd; // 상품코드
	private Long cartQty; // 수량
	private LocalDate cartDate; // 구매일
	
	private MemberVO member; // has a 관계
	private ProdVO prod; // has a 관계
}
