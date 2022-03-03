package com.caglayan.ems.repository;

import com.caglayan.ems.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
