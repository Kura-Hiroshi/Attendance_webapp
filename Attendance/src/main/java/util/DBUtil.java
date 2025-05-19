package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String DRIVER = "org.h2.Driver";
	private static final String URL = "jdbc:h2:tcp://localhost/~/Desktop/勤怠管理アプリ/Attendance_webapp/Attendance/DB/AttendanceDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName(DRIVER); // JDBCドライバのロード
        } catch (ClassNotFoundException e) {
        	throw new IllegalStateException("ドライバのロードに失敗しました");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
