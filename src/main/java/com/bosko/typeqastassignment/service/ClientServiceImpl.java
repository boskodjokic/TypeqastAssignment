package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.exceptions.BadRequestException;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.api.v1.mapper.Mapper;
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
    private Mapper mapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Method for getting the list of clients and returning them to the user.
     *
     * @return list of clients in database
     */
    @Override
    public List<ClientDTO> getAllClients() {

        return clientRepository.findAll()
                .stream()
                .map(mapper::transformClientToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method for getting single client and returning it to the user
     *
     * @param id client id
     * @return single user with the address and list of meter readings
     */
    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(mapper::transformClientToDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Method for creating a new user.
     * First, we are checking if the address given is not null, because full address is needed for saving new client.
     * Then, we are checking if the address is already in the database. If it is, error is displayed to user, because we can't have 2 same addresses in the database.
     * After all checks are passed, new meter is assigned to new user with empty readings.
     * ClientDTO is transformed to entity and saved to database, and that entity in database is transformed back to DTO and displayed to user with all necessary data.
     *
     * @param clientDTO in Postman, data should be posted like below:
     *                  {
     *                  "firstName": "name",
     *                  "lastName": "surname",
     *                  "address": {
     *                  "street": "street",
     *                  "number": "number",
     *                  "city": "city"
     *                  }
     *                  }
     *                  ClientId and AddressId are omitted because they are automatically generated.
     * @return new client is displayed to user and it is saved to the database with meter set for the client and without any readings
     */
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

        Client savedClient = clientRepository.save(mapper.transformClientDTOToEntity(clientDTO));

        return mapper.transformClientToDTO(savedClient);
    }

    /**
     * Method for checking if the given address is not null in any field.
     *
     * @param clientDTO address values are taken from clientDTO
     */
    public void addressNotNullCheck(ClientDTO clientDTO) {
        if (clientDTO.getAddress().getCity() == null || clientDTO.getAddress().getStreet() == null || clientDTO.getAddress().getNumber() == null) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Method for updating existing client.
     * Only first name and last name can be updated.
     * If something is changed in address, it won't be saved.
     * Meter is tied to address, so if the client is moving to another address, he should be given new meter (at least, that is how it works in real life).
     *
     * @param id        clientId is a given parameter
     * @param clientDTO data should be given like below:
     *                  {
     *                  "firstName": "name",
     *                  "lastName": "surname"
     *                  }
     *                  It also works if the same data is given like in method for creating new client (with address), but only first name and last name are updated.
     * @return client with updated fields
     */
    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id).map(client -> {
                    if (clientDTO.getFirstName() != null) {
                        client.setFirstName(clientDTO.getFirstName());
                    }
                    if (clientDTO.getLastName() != null) {
                        client.setLastName(clientDTO.getLastName());
                    }
                    return mapper.transformClientToDTO(clientRepository.save(client));
                }
        ).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Method for deleting client.
     * @param id clientId is passed and repository searches the database and deletes client with corresponding id.
     */
    @Override
    public void deleteClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(!client.isPresent()) {
            throw new ResourceNotFoundException();
        }
        clientRepository.deleteById(id);

    }

    /**
     * Method for searching client with last name.
     *
     * @param lastName is a parameter for searching the repository
     * @return it returns a list of clients, because there is a possibility that there are several clients with same last name.
     * If no clients are present in the list, error is shown to user.
     */
    @Override
    public List<ClientDTO> findByLastName(String lastName) {
        List<Client> clientList = clientRepository.findByLastName(lastName);
        if (clientList.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return clientRepository.findByLastName(lastName)
                .stream()
                .map(mapper::transformClientToDTO)
                .collect(Collectors.toList());

    }

    /**
     * Method for searching client with both first name and last name.
     *
     * @param firstName first name of the searched client
     * @param lastName last name of the searched client
     * @return list of client is returned(there is a possibility that there are more than one client with same first and last name).
     * If no clients are present in the list, error is shown to user.
     */
    @Override
    public List<ClientDTO> findByFirstNameAndLastName(String firstName, String lastName) {
        List<Client> clientList = clientRepository.findByFirstNameAndLastName(firstName,lastName);
        if (clientList.isEmpty()) {
            throw new ResourceNotFoundException();
        }
            return clientRepository.findByFirstNameAndLastName(firstName, lastName)
                    .stream()
                    .map(mapper::transformClientToDTO)
                    .collect(Collectors.toList());
    }
}
