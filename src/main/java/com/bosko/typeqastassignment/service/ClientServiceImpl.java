package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.ClientDTO;
import com.bosko.typeqastassignment.mapper.ClientMapper;
import com.bosko.typeqastassignment.repository.ClientRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientMapper clientMapper, ClientRepository clientRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> getAllClients() {

        return clientRepository.findAll()
                .stream()
                .map(clientMapper::transformToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) throws ChangeSetPersister.NotFoundException {
        return clientRepository.findById(id)
                .map(clientMapper::transformToDTO)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        return null;
    }

    @Override
    public ClientDTO saveClientByDTO(Long id, ClientDTO clientDTO) {
        return null;
    }

    @Override
    public ClientDTO patchClient(Long id, ClientDTO clientDTO) {
        return null;
    }

    @Override
    public void deleteClientById(Long id) {

    }
}
