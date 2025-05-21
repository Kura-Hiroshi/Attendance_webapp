package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceViewDTO {
	private String companyId;
	private int employeeId;
	private String employeeName;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate clockDate;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalDateTime workin;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalDateTime workout;

	public AttendanceViewDTO(String companyId, int employeeId, String employeeName, LocalDate clockDate,
			LocalDateTime workin, LocalDateTime workout) {
		super();
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.clockDate = clockDate;
		this.workin = workin;
		this.workout = workout;
	}
	
	public AttendanceViewDTO(String companyId, int employeeId, String employeeName, java.sql.Date clockDate,
			Timestamp workin, Timestamp workout) {
		super();
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.setClockDate(clockDate);
		this.setWorkin(workin);
		this.setWorkout(workout);
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public LocalDate getClockDate() {
		return clockDate;
	}

	public void setClockDate(LocalDate clockDate) {
		this.clockDate = clockDate;
	}
	
	public void setClockDate(java.sql.Date clockDate) {
		this.clockDate = clockDate != null ? clockDate.toLocalDate():null;
	}

	public LocalDateTime getWorkin() {
		return workin;
	}

	public void setWorkin(LocalDateTime workin) {
		this.workin = workin;
	}
	
	public void setWorkin(Timestamp workin) {
		this.workin = workin != null? workin.toLocalDateTime():null;
	}
	
	public LocalDateTime getWorkout() {
		return workout;
	}

	public void setWorkout(LocalDateTime workout) {
		this.workout = workout;
	}
	
	public void setWorkout(Timestamp workout) {
		this.workout = workout != null ? workout.toLocalDateTime():null;
	}
	
	
}
