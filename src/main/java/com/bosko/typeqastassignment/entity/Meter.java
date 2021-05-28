package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Meter class which is a table in our database.
 * Lombok annotation @Data was used to reduce code for getters, setters and constructors.
 */

@Data
@Entity
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "clientId")
    @OneToOne(mappedBy = "meter")
    private Client client;

    @JsonBackReference(value = "readings")
    @OneToMany(cascade =
            {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE, CascadeType.REFRESH},
            mappedBy = "meter", fetch = FetchType.EAGER)
    private List<Reading> readings = new ArrayList<>();
}
