package com.peoplesphere.notificationservice;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/notifications")
@org.springframework.web.bind.annotation.CrossOrigin(origins="*")
public class NotificationController {
 private final NotificationRepository repo;
 public NotificationController(NotificationRepository repo) { this.repo=repo; }
    @org.springframework.web.bind.annotation.GetMapping public java.util.List<Notification> all() { return repo.findAll(); }
    @org.springframework.web.bind.annotation.PostMapping public org.springframework.http.ResponseEntity<Notification> create(@org.springframework.web.bind.annotation.RequestBody Notification n) { n.id=null; n.createdAt=java.time.LocalDateTime.now(); return org.springframework.http.ResponseEntity.status(201).body(repo.save(n)); }
}