package com.caglayan.ems.controller;

import com.caglayan.ems.model.Address;
import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Employee;
import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.EmployeeDto;
import com.caglayan.ems.model.dto.ManagerDto;
import com.caglayan.ems.service.EmployeeService;
import com.caglayan.ems.service.ManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getAll() throws Exception {
        Employee employee = Employee.builder()
                .name("test-name")
                .mail("test-mail")
                .phoneNumber("05555555555")
                .address(new ArrayList<Address>())
                .department(new Department())
                .build();

        when(employeeService.getAll()).thenReturn(Arrays.asList(employee));

        MvcResult mvcResult = mockMvc.perform(get("/employee")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(employeeService, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Arrays.asList(employee)))
                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void saveEmployee() throws Exception {
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

        ResultActions actions = mockMvc.perform(post("/employee")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(employeeDto)));

        ArgumentCaptor<EmployeeDto> captor = ArgumentCaptor.forClass(EmployeeDto.class);
        verify(employeeService, times(1)).saveEmployee(captor.capture());
        actions.andExpect(status().isOk());
    }

    @Test
    void updateEmployee() throws Exception {
        Employee employee = Employee.builder()
                .name("test-name")
                .mail("test-mail")
                .phoneNumber("05555555555")
                .address(new ArrayList<Address>())
                .department(new Department())
                .build();

        ResultActions actions = mockMvc.perform(put("/employee")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(employee)));

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeService, times(1)).updateEmployee(captor.capture());
        assertEquals(captor.getValue().getId(), employee.getId());
        assertEquals(captor.getValue().getPhoneNumber(), employee.getPhoneNumber());
        actions.andExpect(status().isOk());
    }

    @Test
    void deleteManager() throws Exception {
        long id = 1L;

        doNothing().when(employeeService).deleteManager(id);

        mockMvc.perform(delete("/employee/"+ id)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk());
    }
}