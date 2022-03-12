package com.caglayan.ems.controller;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.DepartmentDto;
import com.caglayan.ems.model.dto.ManagerDto;
import com.caglayan.ems.service.DepartmentService;
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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ManagerController.class)
class ManagerControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ManagerService managerService;

    @Test
    void getAll() throws Exception {
        Manager manager = Manager.builder()
                .name("test-name")
                .build();

        when(managerService.getAll()).thenReturn(Arrays.asList(manager));

        MvcResult mvcResult = mockMvc.perform(get("/manager")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(managerService, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Arrays.asList(manager)))
                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void saveManager() throws Exception {
        ManagerDto managerDto = ManagerDto.builder()
                .name("test-name")
                .build();

        ResultActions actions = mockMvc.perform(post("/manager")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(managerDto)));

        ArgumentCaptor<ManagerDto> captor = ArgumentCaptor.forClass(ManagerDto.class);
        verify(managerService, times(1)).saveManager(captor.capture());
        assertEquals(captor.getValue().getName(), managerDto.getName());
        actions.andExpect(status().isOk());
    }

    @Test
    void deleteManager() throws Exception {
        long id = 1L;

        doNothing().when(managerService).deleteManager(id);

        mockMvc.perform(delete("/manager/"+ id)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk());
    }
}