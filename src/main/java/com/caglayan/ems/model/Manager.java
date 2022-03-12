package com.caglayan.ems.model;

import com.caglayan.ems.model.baseModel.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manager extends BaseEntity {

    private String name;
}
