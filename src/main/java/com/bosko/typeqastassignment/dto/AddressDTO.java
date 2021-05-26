package com.bosko.typeqastassignment.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String city;
}
