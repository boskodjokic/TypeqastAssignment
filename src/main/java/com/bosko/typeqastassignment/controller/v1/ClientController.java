package com.bosko.typeqastassignment.controller.v1;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Client Controller")
@Tag(name = "Client Controller", description = "This is client controller")
@RestController
@RequestMapping(ClientController.BASE_URL)
public class ClientController {

    public static final String BASE_URL = "/api/v1/clients";

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "This will return list of all clients")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @ApiOperation(value = "This will return a client with specified ID")
    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }

    @ApiOperation(value = "This will create new client")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createNewClient(@RequestBody ClientDTO clientDTO) throws Exception {
        return clientService.createNewClient(clientDTO);
    }

    @ApiOperation(value = "This will update existing client")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @ApiOperation(value = "This will delete existing client")
    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClientById(clientId);
    }

    @ApiOperation(value = "This will return a client or a list of clients with specified last name")
    @GetMapping("get/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> findByLastName(@PathVariable("lastName") String lastName) {
        return clientService.findByLastName(lastName);
    }

    @ApiOperation(value = "This will return a client or a list of clients with specified first and last name")
    @GetMapping("/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> findByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return clientService.findByFirstNameAndLastName(firstName, lastName);
    }
}
