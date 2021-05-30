package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@ApiModel(description = "Details about the readings")
@Data
@NoArgsConstructor
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true, name = "Reading ID", value = "1")
    @JsonBackReference(value = "readingId")
    private Long id;

    @ApiModelProperty(name = "Value", value = "25")
    @Column(nullable = false, length = 4)
    private int value;

    @ApiModelProperty(name = "Month", value = "May")
    @Column(nullable = false, length = 100)
    private String month;

    @ApiModelProperty(name = "Year", value = "2021")
    @Column(nullable = false, length = 4)
    private int year;

    @JsonBackReference(value = "meterId")
    @ManyToOne
    private Meter meter;

    public Reading(int value, String month, int year, Meter meter) {
        this.value = value;
        this.month = month;
        this.year = year;
        this.meter = meter;
    }
}
