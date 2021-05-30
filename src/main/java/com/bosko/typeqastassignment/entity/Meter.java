package com.bosko.typeqastassignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Client's meter")
@Data
@Entity
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "Meter ID", value = "1")
    @JsonIgnore
    private Long id;

    @JsonBackReference(value = "clientId")
    @ApiModelProperty(hidden = true)
    @OneToOne(mappedBy = "meter")
    private Client client;


    @OneToMany(cascade =
            {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE, CascadeType.REFRESH},
            mappedBy = "meter", fetch = FetchType.EAGER)
    private List<Reading> readings = new ArrayList<>();
}
