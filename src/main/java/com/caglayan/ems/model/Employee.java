package com.caglayan.ems.model;

import com.caglayan.ems.model.baseModel.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    private String name;
    private String mail;
    private String phoneNumber;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Address> address = new ArrayList<>();

    @ManyToOne
    private Department department;
}
