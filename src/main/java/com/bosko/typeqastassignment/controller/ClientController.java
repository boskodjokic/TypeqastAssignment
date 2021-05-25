package com.bosko.typeqastassignment.controller;

import com.bosko.typeqastassignment.dto.ClientDTO;
import com.bosko.typeqastassignment.dto.ClientListDTO;
import com.bosko.typeqastassignment.service.ClientService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ClientController.BASE_URL)
public class ClientController {

    public static final String BASE_URL = "/api/v1/clients";

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientListDTO getAllClients() {
        return new ClientListDTO(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO createNewClient(@RequestBody ClientDTO clientDTO) throws Exception {
        return clientService.createNewClient(clientDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.saveClientByDTO(id, clientDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO patchClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.patchClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }
}
