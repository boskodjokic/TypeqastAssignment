package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.ClientDTO;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.exceptions.BadRequestException;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.mapper.MapStructMapper;
import com.bosko.typeqastassignment.repository.AddressRepository;
import com.bosko.typeqastassignment.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public List<ClientDTO> getAllClients() {

        return clientRepository.findAll()
                .stream()
                .map(mapStructMapper::transformClientToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(mapStructMapper::transformClientToDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        addressNotNullCheck(clientDTO);
        List<Address> addresses = addressRepository.findAll();
        for (Address address : addresses) {
            if (clientDTO.getAddress().equals(address)) {
                throw new BadRequestException();
            }
        }
        clientDTO.setMeter(new Meter());
        return saveAndReturnDTO(mapStructMapper.transformClientDTOToEntity(clientDTO));
    }

    public ClientDTO saveAndReturnDTO(Client client) {
        Client savedClient = clientRepository.save(client);

        return mapStructMapper.transformClientToDTO(savedClient);

    }

    public void addressNotNullCheck(ClientDTO clientDTO) {
        if (clientDTO.getAddress().getCity() == null || clientDTO.getAddress().getStreet() == null || clientDTO.getAddress().getNumber() == null) {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id).map(client -> {
                    if (clientDTO.getFirstName() != null) {
                        client.setFirstName(clientDTO.getFirstName());
                    }
                    if (clientDTO.getLastName() != null) {
                        client.setLastName(clientDTO.getLastName());
                    }
                    return mapStructMapper.transformClientToDTO(clientRepository.save(client));
                }
        ).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteClientById(Long id) {

        clientRepository.deleteById(id);

    }

    @Override
    public List<ClientDTO> findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName)
                .stream()
                .map(mapStructMapper::transformClientToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public ClientDTO findByFirstNameAndLastName(String firstName, String lastName) {

        Optional<Client>client =clientRepository.findByFirstNameAndLastName(firstName, lastName);

        if(!client.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return mapStructMapper.transformClientToDTO(client.get());
    }
}
