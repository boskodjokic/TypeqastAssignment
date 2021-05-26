package com.bosko.typeqastassignment.dto;

import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Meter;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReadingDTO {

    private Long id;
    private int value;
    private LocalDate createDate;
    private Meter meter;
    private String firstName;
    private String lastName;
    private Address address;
}
