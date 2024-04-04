package kr.or.ddit.prod.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO{
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertProd(ProdVO prod) {
		
		try(
			SqlSession sqlSession = factory.openSession(false);
		){
			int rowcnt = sqlSession.getMapper(ProdDAO.class).insertProd(prod);
			if(rowcnt > 0) sqlSession.commit();
			
			return rowcnt;
		}
	}

	@Override
	public List<ProdVO> selectProdList() {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class); // 마이바티스가 생성해주는 프록시
			return mapperProxy.selectProdList();
		}
	}

	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class); // 마이바티스가 생성해주는 프록시
			return mapperProxy.selectProd(prodId);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class); // 마이바티스가 생성해주는 프록시
			return mapperProxy.updateProd(prod);
		}
	}
}
