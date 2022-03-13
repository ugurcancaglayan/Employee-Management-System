package com.caglayan.ems.model.dto;

import com.caglayan.ems.model.Address;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeUpdateDto {

    private long id;
    private String name;
    private String mail;
    private String phoneNumber;
    private List<Address> addressList = new ArrayList<>();
    private long departmentId;
}
