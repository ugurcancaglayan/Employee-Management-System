package com.caglayan.ems.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDto {

    private String title;
    private String location;
}
