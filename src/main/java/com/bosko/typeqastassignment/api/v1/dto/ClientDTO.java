package com.bosko.typeqastassignment.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@ApiModel(description = "Details about the client")
@Data
public class ClientDTO {

    @ApiModelProperty(notes = "Unique ID of the client", name = "ID", value = "1", hidden = true)
    private Long id;

    @ApiModelProperty(notes = "Client's first name", name = "First Name", required = true, value = "Bob")
    @Column(nullable = false, length = 100)
    private String firstName;

    @ApiModelProperty(notes = "Client's last name", name = "Last Name", required = true, value = "Fossil")
    @Column(nullable = false, length = 100)
    private String lastName;

    @ApiModelProperty(notes = "Client's address", name = "Address")
    @JsonProperty("address")
    private AddressDTO addressDTO;

    @ApiModelProperty(notes = "Client's meter", name = "Meter")
    @JsonProperty("meter")
    private MeterDTO meterDTO;

}
