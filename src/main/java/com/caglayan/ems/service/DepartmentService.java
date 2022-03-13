package com.caglayan.ems.service;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.dto.DepartmentDto;
import com.caglayan.ems.model.dto.DepartmentUpdateDto;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department saveDepartment(DepartmentDto departmentDto);

    Department updateDepartment(DepartmentUpdateDto departmentUpdateDto);

    void deleteDepartment(long id);

    Department getById(long id);
}
