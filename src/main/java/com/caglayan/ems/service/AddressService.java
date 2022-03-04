package com.caglayan.ems.service;

import com.caglayan.ems.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAll();

    Address saveAddress(Address address);

    void deleteAddress(long id);
}
