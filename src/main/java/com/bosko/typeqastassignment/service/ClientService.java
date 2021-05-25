package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.ClientDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id) throws ChangeSetPersister.NotFoundException;

    ClientDTO createNewClient(ClientDTO clientDTO);

    ClientDTO saveClientByDTO(Long id, ClientDTO clientDTO);

    ClientDTO patchClient(Long id, ClientDTO clientDTO);

    void deleteClientById(Long id);
}
