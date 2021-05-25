package com.bosko.typeqastassignment.dto;

import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Meter;
import lombok.Data;

@Data
public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private Meter meter;
}
