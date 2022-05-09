package co.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO_mac {
	static Connection conn;
	static ResultSet rs;
	static PreparedStatement psmt;
	static Statement stmt;

	public static Connection getConnect() {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@jeedb_medium?TNS_ADMIN=C:/db_test/Wallet_JEEDB";
			conn = DriverManager.getConnection(url, "hr", "Tnqtnql12!@#$");
			System.out.println("서버 연결 완");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}

	public static void disconnect() {
		
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
