package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value;
    private LocalDate createDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Meter meter;

    public Reading(int value, LocalDate createDate, Meter meter) {
        this.value = value;
        this.createDate = createDate;
        this.meter = meter;
    }
}
