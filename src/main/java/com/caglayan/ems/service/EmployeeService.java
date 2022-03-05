package com.caglayan.ems.service;

import com.caglayan.ems.model.Employee;
import com.caglayan.ems.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(Employee employee);

    void deleteManager(long id);
}
