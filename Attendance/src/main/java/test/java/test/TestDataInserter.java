package test.java.test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Random;

import model.PasswordHasher;
import util.DBUtil;

public class TestDataInserter {

    public static void main(String[] args) {

        try (Connection con = DBUtil.getConnection()) {
        	con.setAutoCommit(false); // トランザクション開始
        	
        	try {
				
        		deleteAllData(con);
        		insertCompanyData(con);
        		insertEmployeeData(con);
        		insertAttendanceData(con);
        		insertEmployeeNum(con);
        		
        		con.commit(); // コミット
        		System.out.println("✅ テストデータの挿入が完了しました。");
        	} catch (SQLException e) {
        		con.rollback(); // ロールバック
        		e.printStackTrace();
        	}
		} catch (Exception e) {
				e.printStackTrace();
			}
    }

    private static void insertCompanyData(Connection con) throws SQLException, NoSuchAlgorithmException {
        String sql = "INSERT INTO COMPANY (id, company_name, clock_pass, admin_pass) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "c001");
            ps.setString(2, "テスト株式会社");
            ps.setString(3, generateSecurePassword());
            ps.setString(4, generateSecurePassword());
            ps.executeUpdate();
        }
    }

    private static void insertEmployeeData(Connection con) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE (id, company_id, employee_name, pass) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= 5; i++) {
                ps.setInt(1, i);
                ps.setString(2, "c001");
                ps.setString(3, "従業員" + i);
                ps.setString(4, "1234"); // テスト用として簡易
                ps.executeUpdate();
            }
        }
    }

    private static void insertAttendanceData(Connection con) throws SQLException {
        String sql = "INSERT INTO ATTENDANCE (company_id, employee_id, clock_date, event_type, event_time) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            LocalDate baseDate = LocalDate.of(2025, 4, 1);
            for (int i = 0; i < 20; i++) {
                int empId = (i % 5) + 1;
                LocalDate date = baseDate.plusDays(i / 2);
                ps.setString(1, "c001");
                ps.setInt(2, empId);
                ps.setDate(3, Date.valueOf(date));
                ps.setString(4, (i % 2 == 0) ? "出勤" : "退勤");
                ps.setTimestamp(5, Timestamp.valueOf(date.atTime((i % 2 == 0) ? 9 : 18, 0)));
                ps.executeUpdate();
            }
        }
    }

    private static void insertEmployeeNum(Connection con) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE_NUM (company_id, next_employee_id) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "c001");
            ps.setInt(2, 6);
            ps.executeUpdate();
        }
    }

    private static String generateSecurePassword() throws NoSuchAlgorithmException {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int length = 10 + rnd.nextInt(10); // 10〜19文字

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        System.out.println(sb.toString());
        return PasswordHasher.hashPassword(sb.toString());
    }
    
    private static void deleteAllData(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            // 参照関係に注意して順番に削除
            stmt.executeUpdate("DELETE FROM ATTENDANCE");
            stmt.executeUpdate("DELETE FROM EMPLOYEE");
            stmt.executeUpdate("DELETE FROM EMPLOYEE_NUM");
            stmt.executeUpdate("DELETE FROM COMPANY");
        }
    }
}
