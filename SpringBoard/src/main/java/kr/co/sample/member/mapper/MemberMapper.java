package kr.co.sample.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.sample.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	public MemberVO selectMemberForAuth(String username);
	public MemberVO selectMember(String username);
}
