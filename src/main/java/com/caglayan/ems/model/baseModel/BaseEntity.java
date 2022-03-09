package com.caglayan.ems.model.baseModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern = "yyy-mm-dd hh:mm:ss")
    private Date creationDate;

    @JsonFormat(pattern = "yyy-mm-dd hh:mm:ss")
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
