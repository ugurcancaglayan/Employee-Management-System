package com.caglayan.ems.controller;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.DepartmentDto;
import com.caglayan.ems.model.dto.DepartmentUpdateDto;
import com.caglayan.ems.service.DepartmentService;
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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void getAll() throws Exception {
        Department department = Department.builder()
                .name("IT")
                .declaration("Developers - South Department")
                .manager(new Manager())
                .build();

        when(departmentService.getAll()).thenReturn(Arrays.asList(department));

        MvcResult mvcResult = mockMvc.perform(get("/department")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(departmentService, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Arrays.asList(department)))
                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void saveDepartment() throws Exception {
        Manager manager = new Manager();
        manager.setId(1);
        manager.setName("test");

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("test-name");
        departmentDto.setDeclaration("test-declaration");
        departmentDto.setManagerId(manager.getId());

        ResultActions actions = mockMvc.perform(post("/department")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(departmentDto)));

        ArgumentCaptor<DepartmentDto> captor = ArgumentCaptor.forClass(DepartmentDto.class);
        verify(departmentService, times(1)).saveDepartment(captor.capture());
        assertEquals(captor.getValue().getManagerId(), manager.getId());
        actions.andExpect(status().isOk());
    }

    @Test
    void updateDepartment() throws Exception {
        DepartmentUpdateDto department = DepartmentUpdateDto.builder()
                .name("IT")
                .declaration("Developers - South Department")
                .managerId(new Manager().getId())
                .build();

        ResultActions actions = mockMvc.perform(put("/department")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(department)));

        ArgumentCaptor<DepartmentUpdateDto> captor = ArgumentCaptor.forClass(DepartmentUpdateDto.class);
        verify(departmentService, times(1)).updateDepartment(captor.capture());
        assertEquals(captor.getValue().getId(), department.getId());
        actions.andExpect(status().isOk());
    }

    @Test
    void deleteDepartment() throws Exception {
        long id = 1L;

        doNothing().when(departmentService).deleteDepartment(id);

        mockMvc.perform(delete("/department/"+ id)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk());
    }
}