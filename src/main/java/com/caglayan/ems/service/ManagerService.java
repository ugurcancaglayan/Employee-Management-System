package com.caglayan.ems.service;

import com.caglayan.ems.model.Manager;

import java.util.List;

public interface ManagerService {

    List<Manager> getAll();

    Manager saveManager(Manager manager);

    void deleteManager(long id);
}
