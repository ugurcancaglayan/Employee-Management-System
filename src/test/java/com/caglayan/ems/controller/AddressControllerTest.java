package com.caglayan.ems.controller;

import com.caglayan.ems.model.Address;
import com.caglayan.ems.model.dto.AddressDto;
import com.caglayan.ems.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    @Test
    void getAll() throws Exception {
        // given
        Address address = Address.builder()
                .title("test-title")
                .location("test-location")
                .build();

        // when
        when(addressService.getAll()).thenReturn(Arrays.asList(address));

        // then
        MvcResult mvcResult = mockMvc.perform(get("/address")
                .accept(CONTENT_TYPE)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(addressService, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Arrays.asList(address)))
                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void saveAddress() throws Exception {
        // given
        AddressDto addressDto = AddressDto.builder()
                .title("test-title")
                .location("test-location")
                .build();

        // when
        ResultActions actions = mockMvc.perform(post("/address")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(addressDto)));

        // then
        ArgumentCaptor<AddressDto> captor = ArgumentCaptor.forClass(AddressDto.class);
        verify(addressService, times(1)).saveAddress(captor.capture());
        assertThat(captor.getValue().getTitle()).isEqualTo("test-title");
        assertThat(captor.getValue().getLocation()).isEqualTo("test-location");
        actions.andExpect(status().isOk());
    }

    @Test
    void updateAddress() throws Exception {
        // given
        Address address = Address.builder()
                .title("test-title")
                .location("test-location")
                .build();

        // when
        ResultActions actions = mockMvc.perform(put("/address")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(address)));

        // then
        ArgumentCaptor<Address> captor = ArgumentCaptor.forClass(Address.class);
        verify(addressService, times(1)).updateAddress(captor.capture());
        assertThat(captor.getValue().getTitle()).isEqualTo("test-title");
        assertThat(captor.getValue().getLocation()).isEqualTo("test-location");
        actions.andExpect(status().isOk());
    }

    @Test
    void deleteAddress() throws Exception {
        // given
        long id = 1L;

        // when
        doNothing().when(addressService).deleteAddress(id);

        // then
        mockMvc.perform(delete("/address/"+ id)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk());
    }
}