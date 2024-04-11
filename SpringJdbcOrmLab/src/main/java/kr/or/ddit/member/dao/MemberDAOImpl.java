package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSessionFactory factory;
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertMember(MemberVO member) {
		int rowcnt = sqlSession.getMapper(MemberDAO.class).insertMember(member);
		if(rowcnt > 0) sqlSession.commit();
		return rowcnt;
	
	}

	@Override
	public List<MemberVO> selectMemberList() {
		MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class); // 마이바티스가 생성해주는 프록시
		return mapperProxy.selectMemberList();
	}

	@Override
	public MemberVO selectMember(String memId) {
		SqlSession sqlSession = factory.openSession();
		return sqlSession.getMapper(MemberDAO.class).selectMember(memId);
	}

	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 등록된 레코드 수
	 */
	@Override
	public int updateMember(MemberVO member) {
		SqlSession sqlSession = factory.openSession(false);
		int rowcnt = sqlSession.getMapper(MemberDAO.class).updateMember(member);
		if(rowcnt > 0) sqlSession.commit();
		return rowcnt;
	}

	
	@Override
	public int deleteMember(String memId) {
		SqlSession sqlSession = factory.openSession(false);
		int rowcnt = sqlSession.getMapper(MemberDAO.class).deleteMember(memId);
		if(rowcnt > 0) sqlSession.commit();
		return rowcnt;
	}

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		SqlSession sqlSession = factory.openSession(false);
		return sqlSession.getMapper(MemberDAO.class).selectMemberForAuth(memId);	
	}
}
