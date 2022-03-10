package com.caglayan.ems.service.impl;

import com.caglayan.ems.model.Address;
import com.caglayan.ems.model.dto.AddressDto;
import com.caglayan.ems.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void getAll() {
        List<Address> addressList = new ArrayList<>();
        Address address = Address.builder()
                .title("test-title")
                .location("test-location")
                .build();

        addressList.add(address);

        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        List<Address> result = addressService.getAll();

        Assertions.assertEquals(addressList, result);
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    public void saveAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setTitle("test-title");
        addressDto.setLocation("test-location");

        addressService.saveAddress(addressDto);

        //verify(addressRepository, times(1)).save(addressDto);
    }

    @Test
    public void shouldThrowExceptionWhenHasNotId() {
        Address address = new Address();
        when(addressRepository.findById(address.getId()))
                .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(
                NullPointerException.class,
                () -> addressService.updateAddress(address)
        );
    }

    @Test
    public void whenAddressUpdated() {
        Address address = new Address();
        address.setId(1);
        address.setTitle("test");

        when(addressRepository.findById(address.getId()))
                .thenReturn(Optional.of(address));

        when(addressRepository.save(address))
                .thenReturn(address);
        Address updateAddress = addressService.updateAddress(address);

        Assertions.assertEquals(address.getId(), updateAddress.getId());

        verify(addressRepository).save(address);
    }

    @Test
    public void deleteAddress() {
        long id = 1L;

        addressService.deleteAddress(id);
    }

    @Test
    public void getById() {
        long id = 1L;

        Assertions.assertThrows(
                NullPointerException.class,
                () -> addressService.getById(id)
        );
    }
}