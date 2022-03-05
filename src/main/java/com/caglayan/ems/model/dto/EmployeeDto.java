package com.caglayan.ems.model.dto;

import com.caglayan.ems.model.Address;
import com.caglayan.ems.model.Department;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDto {

    private String name;
    private String mail;
    private String phoneNumber;
    private List<Address> addressList = new ArrayList<>();
    private Department department;
}
