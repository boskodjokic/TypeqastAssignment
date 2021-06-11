package com.bosko.typeqastassignment.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "METER")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "meter")
    private Client client;


    @OneToMany(cascade =
            {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE, CascadeType.REFRESH},
            mappedBy = "meter", fetch = FetchType.EAGER)
    private List<Reading> readings = new ArrayList<>();
}
