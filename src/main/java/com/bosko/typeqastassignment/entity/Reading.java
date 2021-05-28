package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

/**
 * Reading class which is a table in our database.
 * Lombok annotation @Data was used to reduce code for getters, setters and constructors.
 */

@Data
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4)
    private int value;

    @Column(nullable = false, length = 100)
    private String month;

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
