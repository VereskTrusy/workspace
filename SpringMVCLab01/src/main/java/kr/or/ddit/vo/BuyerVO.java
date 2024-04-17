package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of = "buyerId")
public class BuyerVO implements Serializable{
	
	private String buyerId; // 거래처아이디
	private String buyerName; // 거래처명
	private String buyerLgu; // 거래처코드
	private String buyerBank; // 은행명
	@Exclude
	@JsonIgnore
	private transient String buyerBankno; // 계좌번호
	private String buyerBankname; // 예금주명
	private String buyerZip; // 우편번호
	private String buyerAdd1; // 기본주소
	private String buyerAdd2; // 상세주소
	private String buyerComtel; // 회사전화번호
	private String buyerFax; // 팩스번호
	private String buyerMail; // 이메일
	private String buyerCharger; // 담당자
	private String buyerTelext; // 전화번호?
	
	// 디비 관계설정시 요게 1번 으로 설정되어야함
	private LprodVO lprod; // 거래처코드 VO, 
	
	private int prodCount; // 거래처별 상품 수
	
	private List<ProdVO> prodList; // has many 관계, BUYER(1) : PROD(n) --> BuyerVO has many ProdVO
}
