package com.caglayan.ems.service;

import com.caglayan.ems.model.Address;
import com.caglayan.ems.model.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<Address> getAll();

    Address saveAddress(AddressDto addressDto);

    Address updateAddress(Address address);

    void deleteAddress(long id);

    Address getById(long id);
}
