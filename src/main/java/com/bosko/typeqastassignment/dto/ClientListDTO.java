package com.bosko.typeqastassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientListDTO {

List<ClientDTO> clients;
}
