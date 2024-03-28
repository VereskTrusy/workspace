package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * Factory Object[Method] Pattern
 * : 객체의 생성을 전담하는 객체를 별도 운영하는 구조.
 * 
 * 오라클이 지원해주는 풀링 데이터 소스 사용
 */
public class ConnectionFactory_JDBC_Ver3 {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	private static PooledConnection pc;

	static{
		try(
			InputStream is = ConnectionFactory_JDBC_Ver3.class.getResourceAsStream("./DbInfo.properties");
		) {
			Properties props = new Properties();
			props.load(is);
			
			OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
			dataSource = ocpds;
			
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			
			ocpds.setURL(url);
			ocpds.setUser(user);
			ocpds.setPassword(password);
			
			// 기본적을 10개의 connection을 만든다. 
			// connection을 close해도 연결을 해제하지 않는다. 
			// 메모리에만 반납하기 때문에 connection 수립 시 바로 획득 가능하다.
			pc = ocpds.getPooledConnection();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = pc.getConnection();
		return conn;
	}
}
