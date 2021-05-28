package com.bosko.typeqastassignment.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * Client class which is a table in our database.
 * Lombok annotation @Data was used to reduce code for getters, setters and constructors.
 */

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meter_id", referencedColumnName = "id")
    private Meter meter;

}
