package com.caglayan.ems.controller;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.dto.DepartmentDto;
import com.caglayan.ems.model.dto.DepartmentUpdateDto;
import com.caglayan.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.saveDepartment(departmentDto));
    }

    @PutMapping
    public ResponseEntity<Department> updateDepartment(@RequestBody DepartmentUpdateDto departmentUpdateDto) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentUpdateDto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
    }
}
