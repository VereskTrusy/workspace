package kr.or.ddit.member.service;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;

import kr.or.ddit.exception.ServiceResult;
import kr.or.ddit.exception.pkNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public ServiceResult createMember(MemberVO member) {
      if(dao.selectMember(member.getMemId())!=null ) {
          return ServiceResult.PKDUPLICATED;
       }
       int rowCnt = dao.insertMember(member);
       if(rowCnt==0) {
          return ServiceResult.FAIL;
       }else {
          return ServiceResult.OK;
       }
    }

	@Override
	public List<MemberVO> retrieveMemberList() {
		List<MemberVO> memberList = null;
		memberList = dao.selectMemberList();
		return memberList;
	}

	@Override
	public MemberVO retrieveMember(String memId) throws pkNotFoundException {
		MemberVO memberVo = dao.selectMember(memId);
		if(memberVo==null) {
			throw new pkNotFoundException(500);
		}
		return memberVo;
	}
  
	@Override
	public ServiceResult modifyMember(MemberVO member) throws pkNotFoundException {
	      if(dao.selectMember(member.getMemId())==null ) {
	          throw new pkNotFoundException(500);
	       }
	      if(!dao.selectMember(member.getMemId()).getMemPass().equals(member.getMemPass()) ) {
	    	  return ServiceResult.INVALIDPASSWORD;
	      }
	       int rowCnt = dao.updateMember(member);
	       if(rowCnt==0) {
	          return ServiceResult.FAIL;
	       }else {
	          return ServiceResult.OK;
	       }
	}


	@Override
	public ServiceResult removeMember(String memId) throws pkNotFoundException {
	    try {
	        int rowCnt = dao.deleteMember(memId);
	        if (rowCnt == 0) {
	            throw new pkNotFoundException(500);
	        }
	        return ServiceResult.OK;
	    } catch (PersistenceException e) {
	        return ServiceResult.FAIL;
	    }
	}
}
