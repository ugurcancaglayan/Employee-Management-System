package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.ManagerDto;
import com.caglayan.ems.repository.ManagerRepository;
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
class ManagerServiceImplTest {

    @InjectMocks
    private ManagerServiceImpl managerService;

    @Mock
    private ManagerRepository managerRepository;

    @Test
    void getAll() {
        List<Manager> managerList = new ArrayList<>();
        Manager manager = Manager.builder()
                .name("test-name")
                .build();

        managerList.add(manager);

        Mockito.when(managerRepository.findAll()).thenReturn(managerList);

        List<Manager> result = managerService.getAll();

        assertEquals(managerList, result);
        verify(managerRepository, times(1)).findAll();
    }

    @Test
    void saveManager() {
        ManagerDto managerDto = ManagerDto.builder()
                .name("test-name")
                .build();

        managerService.saveManager(managerDto);
    }

    @Test
    public void successfulDelete() {
        long id = 1L;

        Assertions.assertThrows(
                NullPointerException.class,
                () -> managerService.deleteManager(id)
        );
    }

    @Test
    public void unsuccessfulDelete() {
        long id = 1L;

        when(managerRepository.findById(id))
                .thenReturn(Optional.empty());

        managerService.deleteManager(id);

        verify(managerRepository, times(1)).findById(id);
    }

    @Test
    void getById() {
        long id = 1L;

        Assertions.assertThrows(
                NullPointerException.class,
                () -> managerService.getById(id)
        );
    }
}