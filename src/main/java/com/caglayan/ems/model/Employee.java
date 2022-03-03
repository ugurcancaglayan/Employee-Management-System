package com.caglayan.ems.model;

import com.caglayan.ems.model.baseModel.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends BaseEntity {

    private String name;
    private String mail;
    private long phoneNumber;
    private String address;

    @ManyToOne
    private Department department;
}
