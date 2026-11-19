package com.peoplesphere.employeeservice;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/employees")
@org.springframework.web.bind.annotation.CrossOrigin(origins="*")
public class EmployeeController {
 private final EmployeeRepository repo;
 public EmployeeController(EmployeeRepository repo) { this.repo=repo; }
    @org.springframework.web.bind.annotation.GetMapping public java.util.List<Employee> all() { return repo.findAll(); }
    @org.springframework.web.bind.annotation.GetMapping("/{id}") public org.springframework.http.ResponseEntity<Employee> get(@org.springframework.web.bind.annotation.PathVariable Long id) { return repo.findById(id).map(org.springframework.http.ResponseEntity::ok).orElse(org.springframework.http.ResponseEntity.notFound().build()); }
    @org.springframework.web.bind.annotation.PostMapping public org.springframework.http.ResponseEntity<Employee> create(@jakarta.validation.Valid @org.springframework.web.bind.annotation.RequestBody Employee e) { e.id=null; return org.springframework.http.ResponseEntity.status(201).body(repo.save(e)); }
    @org.springframework.web.bind.annotation.PutMapping("/{id}") public org.springframework.http.ResponseEntity<Employee> update(@org.springframework.web.bind.annotation.PathVariable Long id,@org.springframework.web.bind.annotation.RequestBody Employee e) { return repo.findById(id).map(old->{ e.id=id; return org.springframework.http.ResponseEntity.ok(repo.save(e)); }).orElse(org.springframework.http.ResponseEntity.notFound().build()); }
    @org.springframework.web.bind.annotation.DeleteMapping("/{id}") public org.springframework.http.ResponseEntity<Void> delete(@org.springframework.web.bind.annotation.PathVariable Long id) { if(!repo.existsById(id)) return org.springframework.http.ResponseEntity.notFound().build(); repo.deleteById(id); return org.springframework.http.ResponseEntity.noContent().build(); }
}