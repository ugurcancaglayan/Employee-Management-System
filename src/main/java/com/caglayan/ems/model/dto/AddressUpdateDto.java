package com.caglayan.ems.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressUpdateDto {

    private long id;
    private String title;
    private String location;
}
