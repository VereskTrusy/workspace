package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory_HikariCP;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
			int rowcnt = sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			if(rowcnt > 0) sqlSession.commit();
			
			return rowcnt;
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			List<MemberVO> memberList = sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
			return memberList;
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			MemberVO member = sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember", memId);
			return member;
		}
	}

	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 등록된 레코드 수
	 */
	@Override
	public int update(MemberVO member) {
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
			int rowcnt = sqlSession.update("kr.or.ddit.member.dao.MemberDAO.update", member);
			if(rowcnt > 0) sqlSession.commit();
			
			return rowcnt;
		}
	}

	
	@Override
	public int delete(String memId) {
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
			int rowcnt = sqlSession.update("kr.or.ddit.member.dao.MemberDAO.delete", memId);
			if(rowcnt > 0) sqlSession.commit();
			
			return rowcnt;
		}
	}

}
