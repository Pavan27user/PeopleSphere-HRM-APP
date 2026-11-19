package com.peoplesphere.notificationservice;
@jakarta.persistence.Entity @jakarta.persistence.Table(name="notifications")
public class Notification {
    @jakarta.persistence.Id @jakarta.persistence.GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY) public Long id;
    public Long employeeId;
    public String channel;
    public String subject;
    @jakarta.persistence.Column(length=2000) public String message;
    public java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();
    public String status = "CREATED";
    public Notification() {}
}