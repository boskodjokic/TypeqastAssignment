package com.bosko.typeqastassignment.bootstrap;

import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.entity.Reading;
import com.bosko.typeqastassignment.repository.AddressRepository;
import com.bosko.typeqastassignment.repository.ClientRepository;
import com.bosko.typeqastassignment.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapLoader implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MeterRepository meterRepository;


//    public BootstrapLoader(ClientRepository clientRepository, AddressRepository addressRepository) {
//        this.clientRepository = clientRepository;
//        this.addressRepository = addressRepository;
//    }

    @Override
    public void run(String... args) throws Exception {
        loadClients();
    }

    public void loadClients() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("Bosko");
        client1.setLastName("Djokic");
        Address address = new Address();
        address.setCity("Beograd");
        address.setNumber("74z");
        address.setStreet("Dragoslava Srejovica");
        addressRepository.save(address);
        client1.setAddress(address);
        Meter meter = new Meter();
        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(12, LocalDate.of(2021, 12, 10), meter));
        readings.add(new Reading(35, LocalDate.of(2021, 11, 15), meter));
        meter.setReadings(readings);
        meterRepository.save(meter);
        client1.setMeter(meter);
        clientRepository.save(client1);


        Client client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Ivana");
        client2.setLastName("Djokic");
        client2.setMeter(new Meter());
        Address address2 = new Address();
        address2.setCity("Loznica");
        address2.setNumber("15");
        address2.setStreet("Vuka Karadzica");
        addressRepository.save(address2);
        client2.setAddress(address2);
        Meter meter2 = new Meter();
        List<Reading> readings2 = new ArrayList<>();
        readings2.add(new Reading(12, LocalDate.of(2021, 10, 1), meter2));
        readings2.add(new Reading(25, LocalDate.of(2021, 7, 12), meter2));
        meter2.setReadings(readings2);
        meterRepository.save(meter2);
        client2.setMeter(meter2);
        clientRepository.save(client2);
    }
}
