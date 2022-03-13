package com.caglayan.ems.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentUpdateDto {

    private long id;
    private String name;
    private String declaration;
    private long managerId;
}
