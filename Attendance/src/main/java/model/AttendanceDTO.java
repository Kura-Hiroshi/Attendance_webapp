package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceDTO {
	private int id;
	private String companyId;
	private int employeeId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate clockDate;
	
	private String eventType;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalDateTime eventTime;

	public AttendanceDTO(int id, String companyId, int employeeId, LocalDate clockDate, String eventType,
			LocalDateTime eventTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.clockDate = clockDate;
		this.eventType = eventType;
		this.eventTime = eventTime;
	}
	
	public AttendanceDTO(int id, String companyId, int employeeId, java.sql.Date clockDate, String eventType,
			java.sql.Timestamp eventTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.setClockDate(clockDate);
		this.eventType = eventType;
		this.setEventTime(eventTime);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getClockDate() {
		return clockDate;
	}

	public void setClockDate(LocalDate clockDate) {
		this.clockDate = clockDate;
	}
	
	public void setClockDate(java.sql.Date clockDate) {
		this.clockDate = clockDate != null ? clockDate.toLocalDate():null;
	}
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}
	
	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime != null? eventTime.toLocalDateTime():null;
	}

	@Override
	public String toString() {
		return "AttendanceDTO [id=" + id + ", companyId=" + companyId + ", employeeId=" + employeeId + ", clockDate="
				+ clockDate + ", eventType=" + eventType + ", workout=" + eventTime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(clockDate, companyId, employeeId, eventType, id, eventTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttendanceDTO other = (AttendanceDTO) obj;
		return Objects.equals(clockDate, other.clockDate) && Objects.equals(companyId, other.companyId)
				&& employeeId == other.employeeId && Objects.equals(eventType, other.eventType) && id == other.id
				&& Objects.equals(eventTime, other.eventTime);
	}
	
	
}
