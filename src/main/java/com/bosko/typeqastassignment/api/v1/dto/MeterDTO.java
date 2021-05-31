package com.bosko.typeqastassignment.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel(description = "Client's meter")
@Data
public class MeterDTO {

    @JsonIgnore
    private Long id;

    @JsonProperty("readings")
    private List<ReadingDTO> readingDTOS;
}
