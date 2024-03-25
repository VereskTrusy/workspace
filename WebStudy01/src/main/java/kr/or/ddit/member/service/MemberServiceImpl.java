package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAOImpl dao;
	@Override
	public List<MemberVO> selectMemberList() {
		return dao.selectMemberList();
	}

}
