package com.caglayan.ems.service;

import com.caglayan.ems.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteManager(long id);
}
