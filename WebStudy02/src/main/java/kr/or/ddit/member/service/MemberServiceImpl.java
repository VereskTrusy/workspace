package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	// 의존관계 설정
	private MemberDAOImpl dao = new MemberDAOImpl();

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		
		if(dao.selectMember(member.getMemId()) == null) {
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
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
		ServiceResult result = null;
		MemberVO srchMember = new MemberVO();
		
		if(dao.selectMember(member.getMemId()) != null) {
			// 회원은 잇음
			srchMember = dao.selectMember(member.getMemId());
			if(member.getMemPass().equals(srchMember.getMemPass())) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		} else {
			// 회원 없음
			result = ServiceResult.FAIL;
		}
		
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException {
		
		return null;
	}
	
	
}
