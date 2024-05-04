package kr.or.ddit.reservation.vo;

import java.util.Date;

import lombok.Data;

/**
* 차량 예약 정보를 처리하기 위한 VO클래스
* @author 노태현
* @since 2023.07.21
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
 *   수정일      	수정자           수정내용
 *  -------    --------    ---------------------------
 *   2023.07.21  노태현          최초 생성
*
* </pre>
*/
@Data
public class VhclRsvtVO {

	private int vRsvtNo;
	private String vnoStr;
	private String userId;
	private String vhclNo;
	private Date rsvtDt;
	private String strtRsvtDt;
	private String endRsvtDt;
	private String prps;
	private String vhclNm;


}
