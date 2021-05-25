package com.bosko.typeqastassignment.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meter_id", referencedColumnName = "id")
    private Meter meter;


    public Client() {
    }

    public Client(String firstName, String lastName, Address address, Meter meter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.meter = meter;
    }
}
