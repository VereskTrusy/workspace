package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private int memNo;
	private String memId;
	private String memPw;
	private String memName;
	private String memNickname;
	private String memRegdate;
	private List<MemberAuthVO> authList;
}
