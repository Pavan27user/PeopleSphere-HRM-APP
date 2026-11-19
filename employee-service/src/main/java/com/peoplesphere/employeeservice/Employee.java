package com.peoplesphere.employeeservice;
@jakarta.persistence.Entity @jakarta.persistence.Table(name="employees")
public class Employee {
    @jakarta.persistence.Id @jakarta.persistence.GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY) public Long id;
    @jakarta.validation.constraints.NotBlank public String firstName;
    @jakarta.validation.constraints.NotBlank public String lastName;
    @jakarta.validation.constraints.Email public String email;
    public String department;
    public String jobTitle;
    public java.math.BigDecimal annualSalary;
    public String status = "ACTIVE";
    public Employee() {}
}