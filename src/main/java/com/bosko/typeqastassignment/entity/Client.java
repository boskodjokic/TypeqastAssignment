package com.bosko.typeqastassignment.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(description = "Details about the client")
@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique ID of the client", name = "ID", required = true, value = "1")
    private Long id;

    @ApiModelProperty(notes = "Client's first name", name = "First Name", required = true, value = "Bob")
    @Column(nullable = false, length = 100)
    private String firstName;

    @ApiModelProperty(notes = "Client's last name", name = "Last Name", required = true, value = "Fossil")
    @Column(nullable = false, length = 100)
    private String lastName;

    @ApiModelProperty(notes = "Client's address", name = "Address")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ApiModelProperty(hidden = true, name = "Meter")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meter_id", referencedColumnName = "id")
    @JsonBackReference("meter")
    private Meter meter;

}
