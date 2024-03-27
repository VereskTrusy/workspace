package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.or.ddit.db.ConnectionFactory_HikariCP;
import kr.or.ddit.db.ConnectionFactory_JDBC_Ver2;
import kr.or.ddit.db.ConnectionFactory_JDBC_Ver3;
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.vo.MemberVO;

class ConnectionPoolingPerformanceCheck {
	static MemberDAO dao;

	// 전체를 대상으로 딱 한번만 실행
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("beforeClass");
		dao = new MemberDAOImpl();
	}

	// 각각의 테스트 케이스보다 먼저 한번씩 실행
	@BeforeEach // Junit 5 버전의 BeforeEach
	void setUp() {
		System.out.println("BeforeEach");
	}
	// Connection Pooling 사용 이전
	// 1. 변경전 - 소요 시간 : 약 1.4초
	// 2. 변경후 - 소요 시간 : 약 0.4초
	// Connection Pooling 사용 이후
	// 1. 변경전 - 소요 시간 : 약 0.4초
	// 2. 변경후 - 소요 시간 : 약 0.4초
	// 장점
	// Latency Time 확 줄음
	// DB서버의 부하가 일정 갯수 이하로만 유지된다.
	@Test
	void testSelectMemberNonCP() {
		for(int i=1; i<=100; i++) {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT 				");
			sql.append("       MEM_ID 			");
			sql.append("     , MEM_PASS 		");
			sql.append("     , MEM_NAME 		");
			sql.append("     , MEM_REGNO1 		");
			sql.append("     , MEM_REGNO2 		");
			sql.append("     , TO_CHAR(MEM_BIR, 'yyyy-mm-dd') AS MEM_BIR ");
			sql.append("     , MEM_ZIP 			");
			sql.append("     , MEM_ADD1 		");
			sql.append("     , MEM_ADD2 		");
			sql.append("     , MEM_HOMETEL 		");
			sql.append("     , MEM_COMTEL 		");
			sql.append("     , MEM_HP 			");
			sql.append("     , MEM_MAIL 		");
			sql.append("     , MEM_JOB 			");
			sql.append("     , MEM_LIKE 		");
			sql.append("     , MEM_MEMORIAL 	");
			sql.append("     , TO_CHAR(MEM_MEMORIALDAY, 'yyyy-mm-dd') AS MEM_MEMORIALDAY ");
			sql.append("     , MEM_MILEAGE 		");
			sql.append("     , MEM_DELETE 		");
			sql.append(" FROM MEMBER 			");
			sql.append(" WHERE MEM_ID = ? 		");
			System.out.println(sql.toString());
			MemberVO member = null;
			try(
				Connection conn = ConnectionFactory_JDBC_Ver2.getConnection(); // 커넥션 팩토리 패턴 사용
				PreparedStatement  pstmt = conn.prepareStatement(sql.toString());
			){
				pstmt.setString(1, "a001");
				System.out.println("Parameter : " + "a001");
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setMemId(rs.getString("MEM_ID"));
					member.setMemPass(rs.getString("MEM_PASS"));
					member.setMemName(rs.getString("MEM_NAME"));
					member.setMemRegno1(rs.getString("MEM_REGNO1"));
					member.setMemRegno2(rs.getString("MEM_REGNO2"));
					member.setMemBir(rs.getString("MEM_BIR"));
					member.setMemZip(rs.getString("MEM_ZIP"));
					member.setMemAdd1(rs.getString("MEM_ADD1"));
					member.setMemAdd2(rs.getString("MEM_ADD2"));
					member.setMemHometel(rs.getString("MEM_HOMETEL"));
					member.setMemComtel(rs.getString("MEM_COMTEL"));
					member.setMemHp(rs.getString("MEM_HP"));
					member.setMemMail(rs.getString("MEM_MAIL"));
					member.setMemJob(rs.getString("MEM_JOB"));
					member.setMemLike(rs.getString("MEM_LIKE"));
					member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
					member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
					member.setMemMileage(rs.getLong("MEM_MILEAGE"));
					member.setMemDelete(rs.getString("MEM_DELETE"));
				}
				rs.close();
			} catch(SQLException e) {
				throw new PersistenceException(e);
			}
		}
	}
	
	@Test
	void testSelectMemberCP() {
		for(int i=1; i<=100; i++) {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT 				");
			sql.append("       MEM_ID 			");
			sql.append("     , MEM_PASS 		");
			sql.append("     , MEM_NAME 		");
			sql.append("     , MEM_REGNO1 		");
			sql.append("     , MEM_REGNO2 		");
			sql.append("     , TO_CHAR(MEM_BIR, 'yyyy-mm-dd') AS MEM_BIR ");
			sql.append("     , MEM_ZIP 			");
			sql.append("     , MEM_ADD1 		");
			sql.append("     , MEM_ADD2 		");
			sql.append("     , MEM_HOMETEL 		");
			sql.append("     , MEM_COMTEL 		");
			sql.append("     , MEM_HP 			");
			sql.append("     , MEM_MAIL 		");
			sql.append("     , MEM_JOB 			");
			sql.append("     , MEM_LIKE 		");
			sql.append("     , MEM_MEMORIAL 	");
			sql.append("     , TO_CHAR(MEM_MEMORIALDAY, 'yyyy-mm-dd') AS MEM_MEMORIALDAY ");
			sql.append("     , MEM_MILEAGE 		");
			sql.append("     , MEM_DELETE 		");
			sql.append(" FROM MEMBER 			");
			sql.append(" WHERE MEM_ID = ? 		");
			System.out.println(sql.toString());
			MemberVO member = null;
			try(
				Connection conn = ConnectionFactory_HikariCP.getConnection(); // 커넥션 팩토리 패턴 사용
				PreparedStatement  pstmt = conn.prepareStatement(sql.toString());
			){
				pstmt.setString(1, "a001");
				System.out.println("Parameter : " + "a001");
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setMemId(rs.getString("MEM_ID"));
					member.setMemPass(rs.getString("MEM_PASS"));
					member.setMemName(rs.getString("MEM_NAME"));
					member.setMemRegno1(rs.getString("MEM_REGNO1"));
					member.setMemRegno2(rs.getString("MEM_REGNO2"));
					member.setMemBir(rs.getString("MEM_BIR"));
					member.setMemZip(rs.getString("MEM_ZIP"));
					member.setMemAdd1(rs.getString("MEM_ADD1"));
					member.setMemAdd2(rs.getString("MEM_ADD2"));
					member.setMemHometel(rs.getString("MEM_HOMETEL"));
					member.setMemComtel(rs.getString("MEM_COMTEL"));
					member.setMemHp(rs.getString("MEM_HP"));
					member.setMemMail(rs.getString("MEM_MAIL"));
					member.setMemJob(rs.getString("MEM_JOB"));
					member.setMemLike(rs.getString("MEM_LIKE"));
					member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
					member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
					member.setMemMileage(rs.getLong("MEM_MILEAGE"));
					member.setMemDelete(rs.getString("MEM_DELETE"));
				}
				rs.close();
			} catch(SQLException e) {
				throw new PersistenceException(e);
			}
		}
	}

	@Test
	void testDummy() {
		System.out.println("test case2");
	}
}
