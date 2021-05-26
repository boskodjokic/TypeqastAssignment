package com.bosko.typeqastassignment.dto;

import java.time.LocalDate;

public interface UserReading {

    String getFirst_name();
    String getLast_name();
    LocalDate getCreate_date();
    int getValue();
    Long getMeter_id();
}
