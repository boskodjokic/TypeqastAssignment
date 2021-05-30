package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@ApiModel(description = "Client's address")
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "Address ID", value = "1")
    @JsonIgnore
    private Long id;

    @ApiModelProperty(name = "Street", value = "Mighty Boosh Avenue")
    @Column(nullable = false, length = 100)
    private String street;

    @ApiModelProperty(name = "Street Number", value = "56")
    @Column(nullable = false, length = 100)
    private String number;

    @ApiModelProperty(name = "City", value = "Dalston")
    @Column(nullable = false, length = 100)
    private String city;

    @ApiModelProperty(hidden = true)
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
