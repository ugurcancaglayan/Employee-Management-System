package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Address;
import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Employee;
import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.EmployeeDto;
import com.caglayan.ems.repository.EmployeeRepository;
import com.caglayan.ems.service.AddressService;
import com.caglayan.ems.service.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private AddressService addressService;

    @Test
    void getAll() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = Employee.builder()
                .name("test-name")
                .mail("test-mail")
                .phoneNumber("05555555555")
                .address(new ArrayList<Address>())
                .department(new Department())
                .build();

        employeeList.add(employee);

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> result = employeeService.getAll();

        assertEquals(employeeList, result);

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void saveEmployee() {
        List<Address> addressList = new ArrayList<>();
        Address address = Address.builder()
                .title("test-title")
                .location("test-location")
                .build();

        addressList.add(address);

        Department department = Department.builder()
                .name("test-department")
                .declaration("test-declaration")
                .manager(new Manager())
                .build();

        EmployeeDto employeeDto = EmployeeDto.builder()
                .name("test-name")
                .mail("test-mail")
                .phoneNumber("05555555555")
                .addressList(addressList)
                .departmentId(department.getId())
                .build();

        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .mail(employeeDto.getMail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .address(employeeDto.getAddressList())
                .department(department)
                .build();

        when(departmentService.getById(department.getId()))
                .thenReturn(department);

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employeeDto);

        assertEquals(employeeDto.getName(), savedEmployee.getName());
        assertEquals(employeeDto.getPhoneNumber(), savedEmployee.getPhoneNumber());
        assertEquals(employeeDto.getAddressList(), savedEmployee.getAddress());
        assertEquals(employeeDto.getDepartmentId(), savedEmployee.getDepartment().getId());

    }

    @Test
    void updateEmployee() {
        Employee employee = Employee.builder()
                .name("test-name")
                .mail("test-mail")
                .phoneNumber("05555555555")
                .address(new ArrayList<Address>())
                .department(new Department())
                .build();

        when(employeeRepository.findById(employee.getId()))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee updatedEmployee = employeeService.updateEmployee(employee);

        Assertions.assertEquals(employee.getId(), updatedEmployee.getId());
        Assertions.assertEquals(employee.getAddress(), updatedEmployee.getAddress());
        Assertions.assertEquals(employee.getDepartment(), updatedEmployee.getDepartment());

    }

    @Test
    public void successfulDelete() {
        long id = 1L;

        Assertions.assertThrows(
                NullPointerException.class,
                () -> employeeService.deleteManager(id)
        );
    }

    @Test
    public void unsuccessfulDelete() {
        long id = 1L;

        when(employeeRepository.findById(id))
                .thenReturn(Optional.empty());

        employeeService.deleteManager(id);

        verify(employeeRepository, times(1)).findById(id);
    }
}