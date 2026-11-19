package com.peoplesphere.attendanceservice;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/attendance")
@org.springframework.web.bind.annotation.CrossOrigin(origins="*")
public class AttendanceController {
 private final AttendanceRepository repo;
 public AttendanceController(AttendanceRepository repo) { this.repo=repo; }
    @org.springframework.web.bind.annotation.GetMapping public java.util.List<Attendance> all() { return repo.findAll(); }
    @org.springframework.web.bind.annotation.PostMapping public org.springframework.http.ResponseEntity<Attendance> create(@org.springframework.web.bind.annotation.RequestBody Attendance a) { a.id=null; if(a.attendanceDate==null) a.attendanceDate=java.time.LocalDate.now(); return org.springframework.http.ResponseEntity.status(201).body(repo.save(a)); }
}