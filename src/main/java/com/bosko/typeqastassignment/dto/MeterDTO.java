package com.bosko.typeqastassignment.dto;

import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Reading;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MeterDTO {

    private Long id;
    private Client client;
    private List<Reading> readings = new ArrayList<>();
}
