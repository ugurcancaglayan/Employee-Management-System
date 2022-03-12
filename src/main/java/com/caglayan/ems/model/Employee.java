package com.caglayan.ems.model;

import com.caglayan.ems.model.baseModel.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
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
