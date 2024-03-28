package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory_HikariCP;
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO member (     ");
		sql.append("	    mem_id,          ");
		sql.append("	    mem_pass,        ");
		sql.append("	    mem_name,        ");
		sql.append("    	mem_regno1,      ");
		sql.append("	    mem_regno2,      ");
		sql.append("	    mem_bir,         ");
		sql.append("	    mem_zip,         ");
		sql.append("	    mem_add1,        ");
		sql.append("	    mem_add2,        ");
		sql.append("	    mem_hometel,     ");
		sql.append("	    mem_comtel,      ");
		sql.append("	    mem_hp,          ");
		sql.append("	    mem_mail,        ");
		sql.append("	    mem_job,         ");
		sql.append("	    mem_like,        ");
		sql.append("	    mem_memorial,    ");
		sql.append("	    mem_memorialday, ");
		sql.append("	    mem_mileage,     ");
		sql.append("	    mem_delete       ");
		sql.append("	) VALUES (           ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    TO_DATE(?, 'yyyy-mm-dd'), ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    ?,               ");
		sql.append("	    TO_DATE(?, 'yyyy-mm-dd'), ");
		sql.append("	    ?,               ");
		sql.append("	    ?                ");
		sql.append("	)                    ");
		
		try(
			Connection conn = ConnectionFactory_HikariCP.getConnection(); // 커넥션 팩토리 패턴 사용
			PreparedStatement  pstmt = conn.prepareStatement(sql.toString());
		){
			int idx = 1;
			pstmt.setString(idx++, member.getMemId());
			pstmt.setString(idx++, member.getMemPass());
			pstmt.setString(idx++, member.getMemName());
			pstmt.setString(idx++, member.getMemRegno1());
			pstmt.setString(idx++, member.getMemRegno2());
			pstmt.setString(idx++, member.getMemBir());
			pstmt.setString(idx++, member.getMemZip());
			pstmt.setString(idx++, member.getMemAdd1());
			pstmt.setString(idx++, member.getMemAdd2());
			pstmt.setString(idx++, member.getMemHometel());
			pstmt.setString(idx++, member.getMemComtel());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setString(idx++, member.getMemLike());
			pstmt.setString(idx++, member.getMemMemorial());
			pstmt.setString(idx++, member.getMemMemorialday());
			if(member.getMemMileage() == null){
				pstmt.setNull(idx++, JDBCType.NUMERIC.ordinal());
			}else {				
				pstmt.setLong(idx++, member.getMemMileage());
			}
			pstmt.setString(idx++, member.getMemDelete());
			
			int rowcnt = pstmt.executeUpdate();
			
			return rowcnt;
		} catch(SQLException e) {
			throw new PersistenceException(e);
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
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE MEMBER             "); 
		sql.append(" SET                       "); 
		sql.append("       MEM_PASS 	   = ? ");
		sql.append("     , MEM_NAME 	   = ? ");
		sql.append("     , MEM_REGNO1 	   = ? ");
		sql.append("     , MEM_REGNO2 	   = ? ");
		sql.append("     , MEM_BIR 		   = ? ");
		sql.append("     , MEM_ZIP 		   = ? ");
		sql.append("     , MEM_ADD1 	   = ? ");
		sql.append("     , MEM_ADD2 	   = ? ");
		sql.append("     , MEM_HOMETEL 	   = ? ");
		sql.append("     , MEM_COMTEL 	   = ? ");
		sql.append("     , MEM_HP 		   = ? ");
		sql.append("     , MEM_MAIL 	   = ? ");
		sql.append("     , MEM_JOB 		   = ? ");
		sql.append("     , MEM_LIKE 	   = ? ");
		sql.append("     , MEM_MEMORIAL    = ? ");
		sql.append("     , MEM_MEMORIALDAY = ? ");  
		sql.append("     , MEM_MILEAGE 	   = ? ");
		sql.append("     , MEM_DELETE 	   = ? ");
		sql.append(" WHERE MEM_ID 		   = ? ");
		
		
		try(
			Connection conn = ConnectionFactory_HikariCP.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(sql.toString());
		){
			int idx = 1;
			pstmt.setString(idx++, member.getMemPass());
			pstmt.setString(idx++, member.getMemName());
			pstmt.setString(idx++, member.getMemRegno1());
			pstmt.setString(idx++, member.getMemRegno2());
			pstmt.setString(idx++, member.getMemBir());
			pstmt.setString(idx++, member.getMemZip());
			pstmt.setString(idx++, member.getMemAdd1());
			pstmt.setString(idx++, member.getMemAdd2());
			pstmt.setString(idx++, member.getMemHometel());
			pstmt.setString(idx++, member.getMemComtel());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setString(idx++, member.getMemLike());
			pstmt.setString(idx++, member.getMemMemorial());
			pstmt.setString(idx++, member.getMemMemorialday());
			if(member.getMemMileage() == null){
				pstmt.setNull(idx++, JDBCType.NUMERIC.ordinal());
			}else {				
				pstmt.setLong(idx++, member.getMemMileage());
			}
			pstmt.setString(idx++, member.getMemDelete());
			pstmt.setString(idx++, member.getMemId());
			
			int rowcnt = pstmt.executeUpdate();
			
			return rowcnt;
		} catch(SQLException e) {
			throw new PersistenceException(e);
		}
	}

	
	@Override
	public int delete(String memId) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE MEMBER 			");
		sql.append(" SET					");
		sql.append("     MEM_DELETE = 'Y'	");
		sql.append(" WHERE MEM_ID = ?		");
		
		try(
			Connection conn = ConnectionFactory_HikariCP.getConnection();
			PreparedStatement  pstmt = conn.prepareStatement(sql.toString());
		){
			int idx = 1;
			pstmt.setString(idx++, memId);
			
			int rowcnt = pstmt.executeUpdate();
			
			return rowcnt;
		} catch(SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
