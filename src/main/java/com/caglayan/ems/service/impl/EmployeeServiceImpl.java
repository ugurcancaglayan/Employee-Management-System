package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Employee;
import com.caglayan.ems.repository.EmployeeRepository;
import com.caglayan.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> oldEmployee = employeeRepository.findById(employee.getId());

        if (oldEmployee.isPresent()) {
            return employeeRepository.save(employee);
        } else
            throw new NullPointerException("Employee Not Found");
    }

    public void deleteManager(long id) {
        employeeRepository.deleteById(id);
    }

}
