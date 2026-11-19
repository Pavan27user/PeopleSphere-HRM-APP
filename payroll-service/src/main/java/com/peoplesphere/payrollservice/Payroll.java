package com.peoplesphere.payrollservice;
@jakarta.persistence.Entity @jakarta.persistence.Table(name="payroll")
public class Payroll {
    @jakarta.persistence.Id @jakarta.persistence.GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY) public Long id;
    public Long employeeId;
    public String payPeriod;
    public java.math.BigDecimal grossPay;
    public java.math.BigDecimal deductions;
    public java.math.BigDecimal netPay;
    public String status = "DRAFT";
    public Payroll() {}
}