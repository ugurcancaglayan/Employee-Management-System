package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.repository.DepartmentRepository;
import com.caglayan.ems.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        Optional<Department> oldDepartment = departmentRepository.findById(department.getId());

        if (oldDepartment.isPresent()) {
            return departmentRepository.save(department);
        } else
            throw new NullPointerException("Department Not Found");
    }

    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }

}
