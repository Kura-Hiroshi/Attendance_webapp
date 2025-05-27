package test.java.test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

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
            ps.setString(1, "xxxxcompany");
            ps.setString(2, "xxxx株式会社");
            String hashedPassword = PasswordHasher.hashPassword("abcd1234");
            ps.setString(3, hashedPassword);
            ps.setString(4, hashedPassword);
            ps.executeUpdate();
        }
    }

    private static void insertEmployeeData(Connection con) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE (id, company_id, employee_name, pass) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 1; i <= 5; i++) {
                ps.setInt(1, i);
                ps.setString(2, "xxxxcompany");
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
            for (int i = 0; i < 10; i++) {
                int empId = (i % 5) + 1;
                LocalDate date = baseDate.plusDays(i);
                // 出勤
                ps.setString(1, "xxxxcompany");
                ps.setInt(2, empId);
                ps.setDate(3, Date.valueOf(date));
                ps.setString(4, "出勤");
                ps.setTimestamp(5, Timestamp.valueOf(date.atTime(9, 0)));
                ps.executeUpdate();
                // 退勤
                ps.setString(1, "xxxxcompany");
                ps.setInt(2, empId);
                ps.setDate(3, Date.valueOf(date));
                ps.setString(4, "退勤");
                ps.setTimestamp(5, Timestamp.valueOf(date.atTime(18, 0)));
                ps.executeUpdate();
            }
        }
    }

    private static void insertEmployeeNum(Connection con) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE_NUM (company_id, next_employee_id) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "xxxxcompany");
            ps.setInt(2, 6);
            ps.executeUpdate();
        }
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
