package com.bosko.typeqastassignment.dto;

import lombok.Data;

@Data
public class UserReadingDTO {

    private String firstName;
    private String lastName;
    private String createDate;
    private int value;
    private Long meterId;

}
