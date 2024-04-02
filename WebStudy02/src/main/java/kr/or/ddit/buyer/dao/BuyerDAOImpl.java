package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class BuyerDAOImpl implements BuyerDAO {
	SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public List<BuyerVO> selectBuyerList() {
		try(
			SqlSession sqlSession = factory.openSession();		
		) {
			BuyerDAO mapperProxy = sqlSession.getMapper(BuyerDAO.class);
			return mapperProxy.selectBuyerList();
		}
	}

	@Override
	public BuyerVO selectBuyer(String buyerId) {
		try(
			SqlSession sqlSession = factory.openSession();		
		) {
			BuyerDAO mapperProxy = sqlSession.getMapper(BuyerDAO.class);
			return mapperProxy.selectBuyer(buyerId);
		}
	}

}
