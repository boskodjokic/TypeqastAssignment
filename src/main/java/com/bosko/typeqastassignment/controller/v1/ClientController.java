package com.bosko.typeqastassignment.controller.v1;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "Returns a list of all clients",
            notes = "Just execute GET method on BASE_URL for clients (/api/v1/clients)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @ApiOperation(value = "Returns a client with specified ID",
            notes = "Provide an ID to look for a specific client")
    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@ApiParam(value = "ID value for the client that you need to be retrieved", required = true) @PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }

    @ApiOperation(value = "This will create new client",
            notes = "Input first name, last name, and full address of new client(street, number, city)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createNewClient(@RequestBody ClientDTO clientDTO) throws Exception {
        return clientService.createNewClient(clientDTO);
    }

    @ApiOperation(value = "This will update existing client",
            notes = "Provide id of client that needs to be updated and input first or last name that should be changed")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @ApiOperation(value = "This will delete existing client",
            notes = "Provide an ID to delete a specific client")
    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteClientById(clientId);
    }

    @ApiOperation(value = "This will return a client or a list of clients with specified last name",
            notes = "Provide a last name to look for a specific client or clients")
    @GetMapping("get/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> findByLastName(@PathVariable("lastName") String lastName) {
        return clientService.findByLastName(lastName);
    }

    @ApiOperation(value = "This will return a client or a list of clients with specified first and last name",
            notes = "Provide a first name and last name to look for a specific client or clients")
    @GetMapping("/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> findByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return clientService.findByFirstNameAndLastName(firstName, lastName);
    }
}
