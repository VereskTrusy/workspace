package kr.or.ddit.servlet10.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory_JDBC_Ver1;
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.servlet10.dao.PropertyDAO;
import kr.or.ddit.utils.NamingUtils;

public class PropertyDAOImpl implements PropertyDAO {

	@Override
	public List<Map<String, Object>> selectProperties(Map<String, Object> paramMap) {
		try(
			Connection conn = ConnectionFactory_JDBC_Ver1.getConnection(); // 커넥션 팩토리 패턴 사용
			Statement stmt = conn.createStatement();	
		){
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
			sql.append(" FROM DATABASE_PROPERTIES ");
			ResultSet rs = stmt.executeQuery(sql.toString());
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			String[] headers = new String[count];
			String[] propsName = new String[count];
			for(int idx=1; idx<=count; idx++){
				headers[idx-1] = metaData.getColumnName(idx);
				propsName[idx-1] = NamingUtils.snakeToCamel(headers[idx-1]);
			}
			List<Map<String,Object>> resultList = new ArrayList<>();
			paramMap.put("headers", headers);
			paramMap.put("propsName", propsName);
			paramMap.put("resultList", resultList);
			while(rs.next()){
				Map<String, Object> propsMap = new HashMap<>();
				resultList.add(propsMap);
				for(String columnName : headers){
					propsMap.put(NamingUtils.snakeToCamel(columnName), rs.getString(columnName));
				}
			}
			rs.close();
			return resultList;
		} catch(SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
