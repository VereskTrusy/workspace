package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAOImpl dao = new MemberDAOImpl();

	@Override
	public ServiceResult createMember(MemberVO member) {
		
		return null;
	}

	@Override
	public List<MemberVO> retriveMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		return memberList;
	}

	@Override
	public MemberVO retriveMember(String memId) throws PkNotFoundException {
		MemberVO member = dao.selectMember(memId);
		
		// member가 null로 예외가 발생하더라도
		// 에러가 아닌 에러코드를 던질라고~
		if(member == null)
			throw new PkNotFoundException(500);
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) throws PkNotFoundException {
		
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException {
		
		return null;
	}
	
	
}
