package com.bosko.typeqastassignment.controller;

import com.bosko.typeqastassignment.dto.ClientDTO;
import com.bosko.typeqastassignment.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ClientController.BASE_URL)
public class ClientController {

    public static final String BASE_URL = "/api/v1/clients";

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createNewClient(@RequestBody ClientDTO clientDTO) throws Exception {
        return clientService.createNewClient(clientDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClientById(clientId);
    }

    @GetMapping("/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> findByLastName(@PathVariable("lastName") String lastName) {
        return clientService.findByLastName(lastName);
    }

    @GetMapping("/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO findByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return clientService.findByFirstNameAndLastName(firstName, lastName);
    }
}
