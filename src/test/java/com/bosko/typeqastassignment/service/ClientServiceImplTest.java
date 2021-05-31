package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.api.v1.dto.MeterDTO;
import com.bosko.typeqastassignment.api.v1.mapper.Mapper;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.repository.AddressRepository;
import com.bosko.typeqastassignment.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    private static final Long ID = 1L;
    private static final String FIRST_NAME = "Bob";
    private static final String LAST_NAME = "Fossil";

    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    Mapper mapper;

    @Mock
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(mapper, clientRepository, addressRepository);
    }

    /**
     * First, we are making 2 Client objects.
     * When we call clientRepository.findAll() that returns our 2 DTOs as list.
     * We are making new list of ClientDTOs and we call clientService.getAllClients();
     * We are asserting that we indeed have 2 items in our list.
     */
    @Test
    void getAllClients() {

        Client client = getClient();

        Client client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Howard");
        client2.setLastName("Moon");

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client, client2));

        List<ClientDTO> clientDTOs = clientService.getAllClients();
        assertEquals(2, clientDTOs.size());
    }

    /**
     * First, we are making one Client object.
     * When we call clientRepository.findById() that returns our client.
     * New ClientDTO is created when calling clientService.getClientById();
     * We are asserting that first name and last name are the same.
     */
    @Test
    void getClientById() {

        Client client = getClient();

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((client)));

        ClientDTO clientDTO = clientService.getClientById(ID);

        assertEquals(FIRST_NAME, clientDTO.getFirstName());
        assertEquals(LAST_NAME, clientDTO.getLastName());
    }

    /**
     * First, we are making one ClientDTO object with all necessary fields.
     * Then, new Client object is created which will have fields from DTO object.
     * When we call clientRepository.save() that returns our savedClient.
     * New ClientDTO is created when calling clientService.createNewClient();
     * We are asserting that first name and last name are the same.
     */
    @Test
    void createNewClient() throws Exception {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(FIRST_NAME);
        Address address1 = new Address();
        address1.setNumber("56");
        address1.setStreet("Mighty Boosh Avenue");
        address1.setCity("Dalston");
        clientDTO.setAddressDTO(mapper.transformAddressToDTO(address1));
        clientDTO.setMeterDTO(new MeterDTO());

        Client savedClient = new Client();

        savedClient.setFirstName(clientDTO.getFirstName());
        savedClient.setLastName(clientDTO.getLastName());
        savedClient.setId(ID);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        ClientDTO savedDTO = clientService.createNewClient(clientDTO);

        assertEquals(clientDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(clientDTO.getLastName(), savedDTO.getLastName());

    }

    /**
     * First, we are making one ClientDTO object with all fields that should be updated.
     * Then, new Client object is created with our helper method.
     * When we call clientRepository.save() and clientRepository.save(), they return our savedClient.
     * New ClientDTO is created when calling clientService.updateClient();
     * We are asserting that first name is the same.
     */
    @Test
    void updateClient() {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(FIRST_NAME);

        Client client = getClient();

        when(clientRepository.findById(anyLong())).thenReturn(Optional.ofNullable(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO savedClientDTO = clientService.updateClient(ID, clientDTO);

        assertEquals(savedClientDTO.getFirstName(), clientDTO.getFirstName());
    }

    /**
     * We are calling clientRepository.deleteById() and verifying that is called exactly one time
     */
    @Test
    void deleteClientById() {
        clientRepository.deleteById(ID);
        verify(clientRepository, times(1)).deleteById(anyLong());
    }

    /**
     * First, we are making one Client object with our helper method.
     * When we call clientRepository.findByLastName() that returns our
     * client(or list if there are several clients with same last name, but for testing purposes, we are getting back only one).
     * New list of ClientDTOs is created when calling clientService.findByLastName();
     * We are asserting that first name and last name are the same.
     */
    @Test
    void findByLastName() {

        Client client = getClient();

        when(clientRepository.findByLastName(anyString())).thenReturn(Collections.singletonList(client));

        List<ClientDTO> clientDTOs = clientService.findByLastName(LAST_NAME);

        assertEquals(FIRST_NAME, clientDTOs.get(0).getFirstName());
        assertEquals(LAST_NAME, clientDTOs.get(0).getLastName());
    }

    /**
     * First, we are making one Client object.
     * When we call clientRepository.findByFirstNameAndLastName() that returns our
     * client(or list if there are several clients with same first and last name, but for testing purposes, we are getting back only one).
     * New list of ClientDTOs is created when calling clientService.findByFirstNameAndLastName();
     * We are asserting that first name and last name are the same.
     */
    @Test
    void findByFirstNameAndLastName() {

        Client client = getClient();

        when(clientRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(Collections.singletonList(client));

        List<ClientDTO> clientDTOs = clientService.findByFirstNameAndLastName(FIRST_NAME, LAST_NAME);

        assertEquals(FIRST_NAME, clientDTOs.get(0).getFirstName());
        assertEquals(LAST_NAME, clientDTOs.get(0).getLastName());
    }

    /**
     * Helper method for creating new client object.
     * @return new client object for testing purposes.
     */
    public Client getClient() {
        Client client = new Client();
        client.setId(ID);
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        return client;
    }
}