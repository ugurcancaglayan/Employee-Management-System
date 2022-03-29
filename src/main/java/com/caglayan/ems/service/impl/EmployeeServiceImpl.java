package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Employee;
import com.caglayan.ems.model.dto.EmployeeDto;
import com.caglayan.ems.model.dto.EmployeeUpdateDto;
import com.caglayan.ems.repository.AddressRepository;
import com.caglayan.ems.repository.EmployeeRepository;
import com.caglayan.ems.service.AddressService;
import com.caglayan.ems.service.DepartmentService;
import com.caglayan.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.caglayan.ems.util.ValidationUtils.phoneNumberValidation;


@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final AddressService addressService;
    private final AddressRepository addressRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(EmployeeDto employeeDto) {
        Department department = departmentService.getById(employeeDto.getDepartmentId());

        employeeDto.getAddressList().stream().map(
                address -> address.equals(addressService.getById(address.getId()))
        ).collect(Collectors.toList());

        if (department != null) {
            if (!phoneNumberValidation(employeeDto.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number's format is not properly!");
            } else {
                    Employee employee = Employee.builder()
                            .name(employeeDto.getName())
                            .mail(employeeDto.getMail())
                            .phoneNumber(employeeDto.getPhoneNumber())
                            .address(employeeDto.getAddressList())
                            .department(department)
                            .build();

                    return employeeRepository.save(employee);
            }
        } else
            throw new IllegalArgumentException("Information about the employee is incorrect!");
    }

    public Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
        Optional<Employee> oldEmployee = employeeRepository.findById(employeeUpdateDto.getId());
        Department department = departmentService.getById(employeeUpdateDto.getDepartmentId());

        if (oldEmployee.isPresent()) {
            Employee updatedEmployee = oldEmployee.get();
            updatedEmployee.setName(employeeUpdateDto.getName());
            updatedEmployee.setMail(employeeUpdateDto.getMail());
            updatedEmployee.setPhoneNumber(employeeUpdateDto.getPhoneNumber());
            updatedEmployee.setDepartment(department);
            updatedEmployee.setAddress(employeeUpdateDto.getAddressList());
            return employeeRepository.save(updatedEmployee);
        } else
            throw new NullPointerException("Employee Not Found");
    }

    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("This id doesn't belong the any employee!")
        );

        employeeRepository.deleteById(id);
    }

}
