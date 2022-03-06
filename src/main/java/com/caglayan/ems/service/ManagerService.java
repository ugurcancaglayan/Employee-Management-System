package com.caglayan.ems.service;

import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.ManagerDto;

import java.util.List;

public interface ManagerService {

    List<Manager> getAll();

    Manager saveManager(ManagerDto managerDto);

    void deleteManager(long id);

    Manager getById(long id);
}
