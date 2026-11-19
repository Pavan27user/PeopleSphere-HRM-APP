package com.peoplesphere.attendanceservice;
@jakarta.persistence.Entity @jakarta.persistence.Table(name="attendance")
public class Attendance {
    @jakarta.persistence.Id @jakarta.persistence.GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY) public Long id;
    public Long employeeId;
    public java.time.LocalDate attendanceDate;
    public String status;
    public String notes;
    public Attendance() {}
}