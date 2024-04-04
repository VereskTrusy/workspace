package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 데이터 맵퍼(ORM)를 사용해서 멀티 엔티티를 조회하는 단계
 * 1. 사용할 엔티티를 결정한다. LPROD, BUYER
 * 2. 엔티티 하나당 하나의 VO 모델링
 * 3. 엔티티 간의 관계를 파악
 * 	  1:1, 1:N, N:M(1:N의 중첩)
 * 4. VO 간의 관계를 엔티티간의 관계를 반영하여 모델링.
 *    1:1(has a), 1:N(has many)
 * 5. 쿼리 작성
 * 6. resultType 대신 resultMap 으로 조회 결과를 바인딩한다.
 *    1:1 : association 으로 바인딩
 *    1:N : collection 으로 바인딩
 * 
 */
@Data
@EqualsAndHashCode(of="lprodGu")
@ToString
public class LprodVO implements Serializable{
	private Long lprodId; // 상품순번
	private String lprodGu; // 상품분류코드
	private String lprodNm; // 상품분류명
	
	private List<BuyerVO> buyerList;
}
