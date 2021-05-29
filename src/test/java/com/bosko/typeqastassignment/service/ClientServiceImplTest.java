package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.api.v1.mapper.Mapper;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
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

    @Test
    void getClientById() {

        Client client = getClient();

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((client)));

        ClientDTO clientDTO = clientService.getClientById(ID);

        assertEquals(FIRST_NAME, clientDTO.getFirstName());
        assertEquals(LAST_NAME, clientDTO.getLastName());
    }

    @Test
    void createNewClient() throws Exception {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(FIRST_NAME);
        Address address1 = new Address();
        address1.setNumber("56");
        address1.setStreet("Mighty Boosh Avenue");
        address1.setCity("Dalston");
        clientDTO.setAddress(address1);
        clientDTO.setMeter(new Meter());

        Client savedClient = new Client();

        savedClient.setFirstName(clientDTO.getFirstName());
        savedClient.setLastName(clientDTO.getLastName());
        savedClient.setId(ID);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        ClientDTO savedDTO = clientService.createNewClient(clientDTO);

        assertEquals(clientDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(clientDTO.getLastName(), savedDTO.getLastName());

    }

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

    @Test
    void deleteClientById() {
        clientRepository.deleteById(ID);
        verify(clientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByLastName() {

        Client client = getClient();

        when(clientRepository.findByLastName(anyString())).thenReturn(Collections.singletonList(client));

        List<ClientDTO> clientDTOs = clientService.findByLastName(LAST_NAME);

        assertEquals(FIRST_NAME, clientDTOs.get(0).getFirstName());
        assertEquals(LAST_NAME, clientDTOs.get(0).getLastName());
    }

    @Test
    void findByFirstNameAndLastName() {

        Client client = getClient();

        when(clientRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(Collections.singletonList(client));

        List<ClientDTO> clientDTOs = clientService.findByFirstNameAndLastName(FIRST_NAME, LAST_NAME);

        assertEquals(FIRST_NAME, clientDTOs.get(0).getFirstName());
        assertEquals(LAST_NAME, clientDTOs.get(0).getLastName());
    }

    public Client getClient() {
        Client client = new Client();
        client.setId(ID);
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        return client;
    }
}