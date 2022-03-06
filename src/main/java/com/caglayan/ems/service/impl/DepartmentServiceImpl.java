package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.DepartmentDto;
import com.caglayan.ems.repository.DepartmentRepository;
import com.caglayan.ems.service.DepartmentService;
import com.caglayan.ems.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ManagerService managerService;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(DepartmentDto departmentDto) {
        Manager manager = managerService.getById(departmentDto.getManagerId());

        if (manager != null) {
            Department department = Department.builder()
                    .name(departmentDto.getName())
                    .declaration(departmentDto.getDeclaration())
                    .manager(manager)
                    .build();

            return departmentRepository.save(department);
        } else
            throw new IllegalArgumentException("Information about the department is incorrect!");
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

    public Department getById(long id) {
        return departmentRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("This id doesn't belong the any department!")
        );
    }

}
