package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO createNewClient(ClientDTO clientDTO) throws Exception;

    ClientDTO saveClientByDTO(Long id, ClientDTO clientDTO);

    ClientDTO patchClient(Long id, ClientDTO clientDTO);

    void deleteClientById(Long id);
}
