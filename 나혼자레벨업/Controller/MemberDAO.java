package Controller;

// 정리 1
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.MemberDTO;

public class MemberDAO {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	private String uid;

	// ===공통사용 메서드===
	// ===1.데이터베이스 연결 메서드===
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
			String user = "cgi_24IS_cloud3_p1_2";
			String password = "smhrd2";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// === 닫기 메서드
	private void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int join(MemberDTO dto) {
		int row1 = 0;

		try {
			getConn();

			String sql = "INSERT INTO TB_USER VALUES(?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());

			row1 = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		// finally
		return row1;
	}
	
	// ===회원가입 메서드
	public int joinUser(MemberDTO dto) {

		int row1 = 0;

		try {
			getConn();

			String sql = "INSERT INTO TB_USER VALUES(?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());

			row1 = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		// finally
		return row1;
	}// join

	// 회원가입 메서드 2
	public int joinIntern(MemberDTO dto) {
		int row2 = 0;

		try {
			getConn();

			String sql = "INSERT  INTO TB_INTERN VALUES(?,?,?,?,?) ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getGender());
			psmt.setInt(3, dto.getScore());
			psmt.setInt(4, dto.getDday());
			psmt.setString(5, dto.getName());

			row2 = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		// finally
		return row2;

	}

	public int update(MemberDTO dto) {
		int row = 0;
		
		try {
			getConn();
			
			String sql ="UPDATE TB_INTERN SET IT_SCORE=?, IT_DDAY = ? WHERE USER_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getScore());
			psmt.setInt(2, dto.getDday());
			psmt.setString(3, uid);
			row = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}
	
	// 로그인 메서드
	public String login(MemberDTO dto) {

		try {
			getConn();

			String sql = "SELECT *FROM TB_USER WHERE USER_ID=? AND USER_PW=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());

			rs = psmt.executeQuery();
			if (rs.next() == true) {
				this.uid = rs.getString("USER_ID");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return this.uid;
	}

	// 정보출력 메서드

	public MemberDTO select(){

		MemberDTO dto = null;

		try {
			getConn();

			String sql = "SELECT IT_NAME,USER_ID,IT_GENDER,IT_SCORE,IT_DDAY FROM TB_INTERN WHERE USER_ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			rs.next();
			String id = rs.getString("USER_ID");
			String gender = rs.getString("IT_GENDER");
			int score = rs.getInt("IT_SCORE");
			int dday = rs.getInt("IT_DDAY");
			String name = rs.getString("IT_NAME");
			dto = new MemberDTO(id, gender, score, dday, name);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return dto;
	}

	public int delete(MemberDTO dto) {

		int row1 = 0;
		int row2 = 0;
		try {
			getConn();

			String sql = "DELETE FROM TB_USER WHERE USER_ID =? AND USER_PW =?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());

			String sql2 = "DELETE FROM TB_INTERN WHERE USER_ID =?";
			psmt = conn.prepareStatement(sql2);

			psmt.setString(1, dto.getId());

			row1 = psmt.executeUpdate();
			row2 = psmt.executeUpdate();

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row1;

	}

} // class
