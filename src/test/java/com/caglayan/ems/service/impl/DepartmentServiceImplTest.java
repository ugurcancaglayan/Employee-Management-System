package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Department;
import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.DepartmentDto;
import com.caglayan.ems.repository.DepartmentRepository;
import com.caglayan.ems.service.ManagerService;
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
class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ManagerService managerService;

    @Test
    void getAll() {
        List<Department> departmentList = new ArrayList<>();
        Department department = Department.builder()
                .name("IT")
                .declaration("Developers - South Department")
                .manager(new Manager())
                .build();

        departmentList.add(department);

        Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);

        List<Department> result = departmentService.getAll();

        assertEquals(departmentList, result);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void saveDepartment() {
        Manager manager = new Manager();
        manager.setId(1);
        manager.setName("test");

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("test-name");
        departmentDto.setDeclaration("test-declaration");
        departmentDto.setManagerId(manager.getId());

        Department department = Department.builder()
                .name(departmentDto.getName())
                .declaration(departmentDto.getDeclaration())
                .manager(manager)
                .build();

        when(managerService.getById(manager.getId())).thenReturn(manager);

        when(departmentRepository.save(department)).thenReturn(department);

        Department savedDepartment = departmentService.saveDepartment(departmentDto);

        assertEquals(departmentDto.getManagerId(), savedDepartment.getManager().getId());
        assertEquals(departmentDto.getName(), savedDepartment.getName());

    }

    @Test
    void updateDepartment() {
        Department department = new Department();
        department.setId(1);
        department.setManager(new Manager());

        when(departmentRepository.findById(department.getId()))
                .thenReturn(Optional.of(department));

        when(departmentRepository.save(department))
                .thenReturn(department);

        Department updateDepartment = departmentService.updateDepartment(department);

        Assertions.assertEquals(department.getId(), updateDepartment.getId());

    }

    @Test
    public void successfulDelete() {
        long id = 1L;

        Assertions.assertThrows(
                NullPointerException.class,
                () -> departmentService.deleteDepartment(id)
        );
    }

    @Test
    public void unsuccessfulDelete() {
        long id = 1L;

        when(departmentRepository.findById(id))
                .thenReturn(Optional.empty());

        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).findById(id);
    }

    @Test
    void getById() {
        long id = 1L;

        Assertions.assertThrows(
                NullPointerException.class,
                () -> departmentService.getById(id)
        );
    }
}