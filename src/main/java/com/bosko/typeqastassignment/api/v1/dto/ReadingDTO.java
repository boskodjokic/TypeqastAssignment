package com.bosko.typeqastassignment.api.v1.dto;

import com.bosko.typeqastassignment.entity.Meter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Data
public class ReadingDTO {

    @JsonBackReference(value = "readingId")
    private Long id;

    private int value;
    private String month;
    private String year;

    @JsonBackReference(value = "meter")
    private Meter meter;

}
