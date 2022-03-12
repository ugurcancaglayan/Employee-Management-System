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
public class Address extends BaseEntity {

    private String title;
    private String location;
}
