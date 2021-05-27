package com.bosko.typeqastassignment.dto;

import com.bosko.typeqastassignment.entity.Meter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingDTO {

    @JsonBackReference(value = "readingId")
    private Long id;

    private int value;
    private String month;
    private String year;

    @JsonBackReference(value = "meter")
    private Meter meter;

}
