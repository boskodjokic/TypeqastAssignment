package com.bosko.typeqastassignment.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value;
    private LocalDate createDate;

    @ManyToOne
    private Meter meter;
}
