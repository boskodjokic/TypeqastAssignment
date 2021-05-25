package com.bosko.typeqastassignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String city;

    @OneToOne(mappedBy = "address")
    private Client client;

    public Address(String street, String number, String city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }
}
