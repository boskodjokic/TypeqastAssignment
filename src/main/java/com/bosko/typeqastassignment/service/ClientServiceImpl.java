package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.ClientDTO;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.mapper.ClientMapper;
import com.bosko.typeqastassignment.repository.AddressRepository;
import com.bosko.typeqastassignment.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientServiceImpl(ClientMapper clientMapper, ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<ClientDTO> getAllClients() {

        return clientRepository.findAll()
                .stream()
                .map(clientMapper::transformToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::transformToDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) throws Exception {
        List<Address> addresses = addressRepository.findAll();
        for (Address address : addresses) {
            if (address.equals(addresses)) {
//                todo
            }
        }
        return saveAndReturnDTO(clientMapper.transformToEntity(clientDTO));
    }

    public ClientDTO saveAndReturnDTO(Client client) {
        Client savedClient = clientRepository.save(client);

        return clientMapper.transformToDTO(savedClient);

    }

    @Override
    public ClientDTO saveClientByDTO(Long id, ClientDTO clientDTO) {
        Client client = clientMapper.transformToEntity(clientDTO);
        client.setId(id);
        return saveAndReturnDTO(client);
    }

    @Override
    public ClientDTO patchClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id).map(client -> {
                    if (clientDTO.getFirstName() != null) {
                        client.setFirstName(clientDTO.getFirstName());
                    }
                    if(clientDTO.getLastName() != null) {
                        client.setLastName(clientDTO.getLastName());
                    }
            return clientMapper.transformToDTO(clientRepository.save(client));
                }
        ).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteClientById(Long id) {

        clientRepository.deleteById(id);

    }
}
