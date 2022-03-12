package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.ManagerDto;
import com.caglayan.ems.repository.ManagerRepository;
import com.caglayan.ems.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    public Manager saveManager(ManagerDto managerDto) {
        Manager manager = Manager.builder()
                .name(managerDto.getName())
                .build();

        return managerRepository.save(manager);
    }

    public void deleteManager(long id) {
        managerRepository.findById(id).orElseThrow(
                () -> new NullPointerException("This id doesn't belong the any manager!")
        );

        managerRepository.deleteById(id);
    }

    public Manager getById(long id) {
        return managerRepository.findById(id).orElseThrow(
                () -> new NullPointerException("This id doesn't belong the any manager!")
        );
    }
}
