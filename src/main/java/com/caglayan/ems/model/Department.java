package com.caglayan.ems.model;

import com.caglayan.ems.model.baseModel.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department extends BaseEntity {

    private String name;
    private String declaration;

    @ManyToOne
    private Manager manager;
}
