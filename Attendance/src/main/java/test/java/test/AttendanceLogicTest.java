package test.java.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.AttendanceDTO;
import model.AttendanceLogic;

class AttendanceLogicTest {

	@Test
	void test() throws SQLException {
		SqlForTest.init();
		SqlForTest.addCompany();
		SqlForTest.addEmployee();
		AttendanceDTO attDTO =  AttendanceLogic.execute(1, "C001", "1234", "出勤");
		assertNotNull(attDTO.getEventTime());
		LocalDate ld = LocalDate.now();
		LocalDateTime ldt = LocalDateTime.now();
		attDTO.setEventTime(ldt);
		assertEquals(new AttendanceDTO(1, "C001", 1, ld, "出勤", ldt),attDTO );
		
	}

}
