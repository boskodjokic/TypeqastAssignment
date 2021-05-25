package com.bosko.typeqastassignment.bootstrap;

import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.repository.AddressRepository;
import com.bosko.typeqastassignment.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapLoader implements CommandLineRunner {

    private ClientRepository clientRepository;
    private AddressRepository addressRepository;

    public BootstrapLoader(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        loadAddresses();
        loadClients();
    }

    public void loadClients() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("Bosko");
        client1.setLastName("Djokic");
        Address address = new Address();
        address.setCity("Beograd");
        address.setId(1L);
        addressRepository.save(address);
        client1.setAddress(address);
        client1.setMeter(new Meter());
        clientRepository.save(client1);


        Client client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Ivana");
        client2.setLastName("Djokic");
        client2.setMeter(new Meter());

        clientRepository.save(client2);
    }

    public void loadAddresses() {
        Address address1 = new Address();
        address1.setId(1L);
        address1.setCity("Beograd");
        address1.setStreet("Dragoslava Srejovica");
        address1.setNumber("74");
        addressRepository.save(address1);

        Address address2 = new Address();
        address2.setId(2L);
        address2.setCity("Beograd");
        address2.setStreet("Dragoslava Srejovica");
        address2.setNumber("74");
        addressRepository.save(address2);
    }
}
