package test.java.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AttendanceLogic;
import model.Employee;



public class AttendanceLogicTest {
    private static Connection con;

    @BeforeAll
    static void setup() throws Exception {
        Class.forName("org.h2.Driver");
        con = DriverManager.getConnection("jdbc:h2:./DB/AttendanceDB", "sa", "");
    }

    @AfterAll
    static void teardown() throws SQLException {
        con.close();
    }

    @BeforeEach
    void clearAttendanceTable() throws SQLException {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DELETE FROM ATTENDANCE");
        }
    }

    @Test
    void testExecute_AttendanceInserted() throws SQLException {
        // Arrange
        Employee employee = new Employee(1, "xxxxcompany");
        String eventType = "CLOCK_IN";

        // Act
        AttendanceLogic.execute(employee, eventType);

        // Assert
        try (Connection con = DriverManager.getConnection("jdbc:h2:./DB/AttendanceDB", "sa", "");
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ATTENDANCE WHERE EMPLOYEE_ID = ? AND COMPANY_ID = ?")) {

            pstmt.setInt(1, 1);
            pstmt.setString(2, "xxxxcompany");

            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next(), "ATTENDANCEレコードが挿入されているべきです");
            assertEquals("CLOCK_IN", rs.getString("EVENT_TYPE"));
            assertNotNull(rs.getTimestamp("EVENT_TIME"));
        }
    }
}
