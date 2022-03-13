package com.caglayan.ems.service;

import com.caglayan.ems.model.Employee;
import com.caglayan.ems.model.dto.EmployeeDto;
import com.caglayan.ems.model.dto.EmployeeUpdateDto;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto);

    void deleteManager(long id);
}
