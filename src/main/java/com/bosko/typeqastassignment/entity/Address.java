package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * Address class which is a table in our database.
 * Lombok annotation @Data was used to reduce code for getters, setters and constructors.
 * equals and hashCode methods were overridden because we needed to check equality of address object in one of the methods.
 */
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String street;
    @Column(nullable = false, length = 100)
    private String number;
    @Column(nullable = false, length = 100)
    private String city;

    @JsonBackReference(value = "client")
    @OneToOne(mappedBy = "address")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return Objects.equals(street,address.street) && Objects.equals(number, address.number)
                && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }

}
