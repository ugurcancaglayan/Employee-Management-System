package com.caglayan.ems.controller;

import com.caglayan.ems.model.Manager;
import com.caglayan.ems.service.impl.ManagerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerServiceImpl managerService;

    @GetMapping
    public ResponseEntity<List<Manager>> getAll() {
        return ResponseEntity.ok(managerService.getAll());
    }

    @PostMapping
    public ResponseEntity<Manager> saveManager(@RequestBody Manager manager) {
        return ResponseEntity.ok(managerService.saveManager(manager));
    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable long id) {
        managerService.deleteManager(id);
    }
}
