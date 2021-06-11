package com.bosko.typeqastassignment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "READING")
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

    @ManyToOne
    private Meter meter;

    public Reading(int value, String month, int year, Meter meter) {
        this.value = value;
        this.month = month;
        this.year = year;
        this.meter = meter;
    }
}
