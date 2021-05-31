package com.bosko.typeqastassignment.api.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Details about client's address")
@Data
public class AddressDTO {

    @ApiModelProperty(name = "Street", value = "Mighty Boosh Avenue", required = true)
    private String street;

    @ApiModelProperty(name = "Street Number", value = "56", required = true)
    private String number;

    @ApiModelProperty(name = "City", value = "Dalston", required = true)
    private String city;

}
