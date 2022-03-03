package com.caglayan.ems.model.baseModel;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date creationDate;

    private Date lastModifiedDate;

    @PrePersist
    public void onPrePersist() {
        creationDate = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        lastModifiedDate = new Date();
    }
}
