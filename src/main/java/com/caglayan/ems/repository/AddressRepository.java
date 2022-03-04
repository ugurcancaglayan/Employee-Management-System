package com.caglayan.ems.repository;

import com.caglayan.ems.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
