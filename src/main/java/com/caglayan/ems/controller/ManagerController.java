package com.caglayan.ems.controller;

import com.caglayan.ems.model.Manager;
import com.caglayan.ems.model.dto.ManagerDto;
import com.caglayan.ems.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Manager>> getAll() {
        return ResponseEntity.ok(managerService.getAll());
    }

    @PostMapping
    public ResponseEntity<Manager> saveManager(@RequestBody ManagerDto managerDto) {
        return ResponseEntity.ok(managerService.saveManager(managerDto));
    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable long id) {
        managerService.deleteManager(id);
    }
}
