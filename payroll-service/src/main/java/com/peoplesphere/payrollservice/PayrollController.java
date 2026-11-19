package com.peoplesphere.payrollservice;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/payroll")
@org.springframework.web.bind.annotation.CrossOrigin(origins="*")
public class PayrollController {
 private final PayrollRepository repo;
 public PayrollController(PayrollRepository repo) { this.repo=repo; }
    @org.springframework.web.bind.annotation.GetMapping public java.util.List<Payroll> all() { return repo.findAll(); }
    @org.springframework.web.bind.annotation.PostMapping public org.springframework.http.ResponseEntity<Payroll> create(@org.springframework.web.bind.annotation.RequestBody Payroll p) { p.id=null; if(p.grossPay!=null && p.deductions!=null) p.netPay=p.grossPay.subtract(p.deductions); return org.springframework.http.ResponseEntity.status(201).body(repo.save(p)); }
}