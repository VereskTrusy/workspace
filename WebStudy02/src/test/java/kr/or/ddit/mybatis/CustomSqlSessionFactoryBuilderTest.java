package kr.or.ddit.mybatis;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

class CustomSqlSessionFactoryBuilderTest {

	@Test
	void testGetSqlSessionFactory() {
		SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
		System.out.println(factory);
		
		
		try(
			SqlSession session = factory.openSession(); // 한매서드 안에서 지역코드 처럼 사용
		){
			System.out.println(session);
		}
	}

}
