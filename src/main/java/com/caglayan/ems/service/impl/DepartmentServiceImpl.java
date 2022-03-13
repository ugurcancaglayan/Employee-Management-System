package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.DepartmentDto;
import com.caglayan.ems.model.dto.DepartmentUpdateDto;
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

    public Department updateDepartment(DepartmentUpdateDto departmentUpdateDto) {
        Optional<Department> oldDepartment = departmentRepository.findById(departmentUpdateDto.getId());
        Manager manager = managerService.getById(departmentUpdateDto.getManagerId());

        if (oldDepartment.isPresent()) {
            Department updatedDepartment = oldDepartment.get();
            updatedDepartment.setName(departmentUpdateDto.getName());
            updatedDepartment.setDeclaration(departmentUpdateDto.getDeclaration());
            updatedDepartment.setManager(manager);
            return departmentRepository.save(updatedDepartment);
        } else
            throw new NullPointerException("Department Not Found");
    }

    public void deleteDepartment(long id) {
        departmentRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("This id doesn't belong the any department!")
        );

        departmentRepository.deleteById(id);
    }

    public Department getById(long id) {
        return departmentRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("This id doesn't belong the any department!")
        );
    }

}
