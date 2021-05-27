package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value;
    private String month;
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
