package test.java.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.AttendanceDAO;
import dao.EmployeeDAO;

class DAOTest {
    private static Connection con;

    @BeforeAll
    static void setUpDatabase() throws SQLException {
        con = DriverManager.getConnection("jdbc:h2:./DB/AttendanceDB","sa","");
        }

    @AfterAll
    static void tearDown() throws SQLException {
        con.close();
    }

    @Test
    void testFindEmployeeById() throws SQLException {
        ResultSet rs = EmployeeDAO.findEmployeeById(1, "xxxxcompany","1234", con);
        assertNotNull(rs);
        assertTrue(rs.next());
        assertEquals(1, rs.getInt("ID"));
        assertEquals("xxxxcompany", rs.getString("COMPANY_ID"));
        assertEquals("山田　一郎", rs.getString("employee_name"));
        rs.close();
    }

    @Test
    void testInsertAttendance() throws SQLException {
        AttendanceDAO.insert("xxxxcompany", 1, "出勤", con);

        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ATTENDANCE WHERE ID = ?")) {
            pstmt.setInt(1, 1);
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("xxxxcompany", rs.getString("COMPANY_ID"));
            assertEquals(1, rs.getInt("EMPLOYEE_ID"));
            assertEquals("出勤", rs.getString("EVENT_TYPE"));
            assertNotNull(rs.getTimestamp("EVENT_TIME"));
            rs.close();
        }
    }
}
