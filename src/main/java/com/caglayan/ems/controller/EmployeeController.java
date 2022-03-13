package com.caglayan.ems.controller;

import com.caglayan.ems.model.Employee;
import com.caglayan.ems.model.dto.EmployeeDto;
import com.caglayan.ems.model.dto.EmployeeUpdateDto;
import com.caglayan.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDto));
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeUpdateDto employeeUpdateDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeUpdateDto));
    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable long id) {
        employeeService.deleteManager(id);
    }
}
