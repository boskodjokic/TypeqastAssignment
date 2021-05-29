package com.bosko.typeqastassignment.controller.v1;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.controller.RestResponseEntityExceptionHandler;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController clientController;

    MockMvc mockMvc;

    /**
     * Necessary set up before all the tests.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    /**
     * First, we are making 2 DTO objects and putting them in the list.
     * When we call clientService.getAllClients() that returns our list of clients.
     * Than mockMvc gets the BASE_URL and performs get method that accepts JSON format.
     * We expect the status is OK and that we have 2 objects in our list.
     */
    @Test
    void getAllClients() throws Exception {

        ClientDTO client1 = new ClientDTO();
        client1.setId(1L);
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");

        ClientDTO client2 = new ClientDTO();
        client2.setId(2L);
        client2.setFirstName("Howard");
        client2.setLastName("Moon");

        List<ClientDTO> clients = Arrays.asList(client1, client2);

        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders.get(ClientController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    /**
     * First, we are making a DTO object.
     * When we call clientService.getClientById() that returns our client.
     * Than mockMvc gets the BASE_URL/1 and performs get method that accepts JSON format.
     * We expect the status is OK and that first name is the one we set for client1.
     */
    @Test
    void getClientById() throws Exception {

        ClientDTO client1 = new ClientDTO();
        client1.setId(1L);
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");

        when(clientService.getClientById(anyLong())).thenReturn(client1);

        mockMvc.perform(MockMvcRequestBuilders.get(ClientController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Bob")));
    }

    /**
     * First, we are making a DTO object.
     * After that we are making new DTO object that will hold data from the first object.
     * When we call clientService.createNewClient() with first object, that returns second object.
     * Than mockMvc calls post on the BASE_URL that accepts JSON format.
     * We expect the status is Created and that first name is the one we set for our objects.
     */
    @Test
    void createNewClient() throws Exception {

        ClientDTO client1 = new ClientDTO();
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");

        ClientDTO returnDTO = new ClientDTO();
        returnDTO.setFirstName(client1.getFirstName());
        returnDTO.setLastName(client1.getLastName());

        when(clientService.createNewClient(returnDTO)).thenReturn(client1);

        mockMvc.perform(post(ClientController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().valueToTree(returnDTO).toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Bob")));
    }

    /**
     * First, we are making a DTO object.
     * After that we are making new DTO object that will hold data from the first object.
     * When we call clientService.updateClient() with first object, that returns second object.
     * Than mockMvc calls put on the BASE_URL/1 that accepts JSON format.
     * We expect the status is OK and that first name is the one we set for our objects.
     */
    @Test
    void updateClient() throws Exception {

        ClientDTO client1 = new ClientDTO();
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");

        ClientDTO returnDTO = new ClientDTO();
        returnDTO.setFirstName(client1.getFirstName());
        returnDTO.setLastName(client1.getLastName());

        when(clientService.updateClient(anyLong(), any(ClientDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put(ClientController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().valueToTree(returnDTO).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Bob")));
    }

    /**
     * We call clientService.deleteClient() on BASE_URL/1.
     * We expect the status is OK and we are verifying that clientService gets called with method deleteClientById.
     */
    @Test
    void deleteClient() throws Exception {

        mockMvc.perform(delete(ClientController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

            verify(clientService).deleteClientById(anyLong());
    }

    /**
     * First, we are making a DTO object.
     * When we call clientService.findByLastName() that returns the list of clients.
     * Than mockMvc gets the BASE_URL/get/Fossil and performs get method that accepts JSON format.
     * We expect the status is OK and that first name is the one we set for client1.
     */
    @Test
    void findByLastName() throws Exception {

        ClientDTO client1 = new ClientDTO();
        client1.setId(1L);
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");

        List<ClientDTO> clients = Arrays.asList(client1);

        when(clientService.findByLastName(anyString())).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders.get(ClientController.BASE_URL + "/get/Fossil")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName", equalTo("Bob")));
    }

    /**
     * First, we are making a DTO object.
     * When we call clientService.findByFirstNameAndLastName() that returns the list of clients.
     * Than mockMvc gets the BASE_URL/Bob/Fossil and performs get method that accepts JSON format.
     * We expect the status is OK and that first name is the one we set for client1.
     */
    @Test
    void findByFirstNameAndLastName() throws Exception {
        ClientDTO client1 = new ClientDTO();
        client1.setId(1L);
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");

        List<ClientDTO> clients = Arrays.asList(client1);

        when(clientService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(clients);
        mockMvc.perform(MockMvcRequestBuilders.get(ClientController.BASE_URL + "/Bob/Fossil")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName", equalTo("Bob")));
    }

    /**
     *We are calling getClientById() with some non-existing ID and expecting that the exception is thrown.
     */
    @Test
    void notFoundException() throws Exception {
        when(clientService.getClientById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(ClientController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}