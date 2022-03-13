package com.caglayan.ems.controller;

import com.caglayan.ems.model.Address;
import com.caglayan.ems.model.dto.AddressDto;
import com.caglayan.ems.model.dto.AddressUpdateDto;
import com.caglayan.ems.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.saveAddress(addressDto));
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@RequestBody AddressUpdateDto addressUpdateDto) {
        return ResponseEntity.ok(addressService.updateAddress(addressUpdateDto));
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
    }
}
