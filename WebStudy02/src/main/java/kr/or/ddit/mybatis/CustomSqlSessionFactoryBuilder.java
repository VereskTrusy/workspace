package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	
	// static{ } 코드블럭은 메모리에 클래스가 로딩 될때 한번 실행된다.
	static{
		String resource = "kr/or/ddit/mybatis/Configuration.xml"; // 읽어들일 파일 경로 논리경로로 설정
		try(
			Reader reader = Resources.getResourceAsReader(resource);
		){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
