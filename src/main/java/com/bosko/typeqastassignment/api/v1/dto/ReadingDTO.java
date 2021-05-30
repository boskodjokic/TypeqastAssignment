package com.bosko.typeqastassignment.api.v1.dto;

import com.bosko.typeqastassignment.entity.Meter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@ApiModel(description = "Details about the readings")
@Data
public class ReadingDTO {

    @ApiModelProperty(hidden = true, name = "Reading ID", value = "1")
    @JsonIgnore
    private Long id;

    @ApiModelProperty(name = "Value", value = "25")
    @Column(nullable = false, length = 4)
    private int value;

    @ApiModelProperty(name = "Month", value = "May")
    @Column(nullable = false, length = 100)
    private String month;

    @ApiModelProperty(name = "Year", value = "2021")
    @Column(nullable = false, length = 4)
    private String year;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Meter meter;

}
