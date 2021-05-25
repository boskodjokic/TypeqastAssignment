package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne(mappedBy = "meter")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meter")
    private List<Reading> readings;
}
