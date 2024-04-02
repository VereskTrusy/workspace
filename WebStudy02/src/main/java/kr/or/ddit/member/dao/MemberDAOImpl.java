package kr.or.ddit.member.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
	
	private MemberDAO generateProxy(SqlSession sqlSession) {
		return (MemberDAO) Proxy.newProxyInstance(MemberDAO.class.getClassLoader(), new Class[] {MemberDAO.class}, new InvocationHandler() {
			// 인터페이스가 없으면 프록시를 생성하지 못한다. -> 인터페이스가 있어야 프록시를 생성할 수 있다.
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
				String nameSpace = method.getDeclaringClass().getName(); // 인터페이스의 참조 얻기, 퀄러파이드 네임 반환
				String id = method.getName(); // 메소드명 반환
				String statement = nameSpace + "." + id;
				Object argument = null;
				
				if(args!=null && args.length > 0) {
					argument = args[0];
				}
				if(method.getReturnType().equals(List.class)) {
					return sqlSession.selectList(statement, argument);					
				}else {
					return sqlSession.selectOne(statement, argument);
				}
			}
		});
	}

	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
//			int rowcnt = sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			int rowcnt = sqlSession.getMapper(MemberDAO.class).insertMember(member);
			if(rowcnt > 0) sqlSession.commit();
			
			return rowcnt;
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			//List<MemberVO> memberList = sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
			//return memberList;
			
			//return generateProxy(sqlSession).selectMemberList();
			
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class); // 마이바티스가 생성해주는 프록시
			return mapperProxy.selectMemberList();
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			//MemberVO member = sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember", memId);
			//return member;
			
			// return generateProxy(sqlSession).selectMember(memId);
			
			return sqlSession.getMapper(MemberDAO.class).selectMember(memId);
		}
	}

	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 등록된 레코드 수
	 */
	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
			// int rowcnt = sqlSession.update("kr.or.ddit.member.dao.MemberDAO.updateMember", member);
			int rowcnt = sqlSession.getMapper(MemberDAO.class).updateMember(member);
			if(rowcnt > 0) sqlSession.commit();
			
			return rowcnt;
		}
	}

	
	@Override
	public int deleteMember(String memId) {
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
			//int rowcnt = sqlSession.update("kr.or.ddit.member.dao.MemberDAO.deleteMember", memId);
			int rowcnt = sqlSession.getMapper(MemberDAO.class).deleteMember(memId);
			if(rowcnt > 0) sqlSession.commit();
			
			return rowcnt;
		}
	}

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
			return sqlSession.getMapper(MemberDAO.class).selectMemberForAuth(memId);	
		}
	}
}
