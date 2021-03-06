package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO createNewClient(ClientDTO clientDTO) throws Exception;

    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    void deleteClientById(Long id);

    List<ClientDTO> findByLastName(String lastName);

    List<ClientDTO> findByFirstNameAndLastName(String firstName, String lastName);
}
