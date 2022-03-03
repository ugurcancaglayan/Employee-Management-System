package com.caglayan.ems.service;

import com.caglayan.ems.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    void deleteDepartment(long id);
}
