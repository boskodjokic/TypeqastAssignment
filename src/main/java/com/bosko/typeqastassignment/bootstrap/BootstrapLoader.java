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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("DEFAULT")
public class BootstrapLoader implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MeterRepository meterRepository;

    @Override
    public void run(String... args) throws Exception {
        loadClients();
    }

    public void loadClients() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");
        Address address = new Address();
        address.setCity("London");
        address.setNumber("56");
        address.setStreet("Bob's Street");
        addressRepository.save(address);
        client1.setAddress(address);
        Meter meter = new Meter();
        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(12, "February", 2021, meter));
        readings.add(new Reading(35, "March", 2021, meter));
        readings.add(new Reading(15, "March", 2020, meter));
        readings.add(new Reading(25, "June", 2020, meter));
        readings.add(new Reading(37, "August", 2021, meter));
        meter.setReadings(readings);
        meterRepository.save(meter);
        client1.setMeter(meter);
        clientRepository.save(client1);


        Client client2 = new Client();
        client2.setId(2L);
        client2.setFirstName("Howard");
        client2.setLastName("Moon");
        client2.setMeter(new Meter());
        Address address2 = new Address();
        address2.setCity("Dalston");
        address2.setNumber("12A");
        address2.setStreet("Howard's Avenue");
        addressRepository.save(address2);
        client2.setAddress(address2);
        Meter meter2 = new Meter();
        List<Reading> readings2 = new ArrayList<>();
        readings2.add(new Reading(12, "May", 2021, meter2));
        readings2.add(new Reading(25, "June", 2021, meter2));
        readings2.add(new Reading(10, "July", 2021, meter2));
        readings2.add(new Reading(16, "August", 2021, meter2));
        readings2.add(new Reading(29, "March", 2021, meter2));
        meter2.setReadings(readings2);
        meterRepository.save(meter2);
        client2.setMeter(meter2);
        clientRepository.save(client2);

        Client client3 = new Client();
        client3.setId(3L);
        client3.setFirstName("Vince");
        client3.setLastName("Noir");
        client3.setMeter(new Meter());
        Address address3 = new Address();
        address3.setCity("Birmingham");
        address3.setNumber("87");
        address3.setStreet("Vince's Boulevard");
        addressRepository.save(address3);
        client3.setAddress(address3);
        Meter meter3 = new Meter();
        List<Reading> readings3 = new ArrayList<>();
        readings3.add(new Reading(12, "January", 2021, meter3));
        readings3.add(new Reading(25, "February", 2021, meter3));
        readings3.add(new Reading(34, "July", 2020, meter3));
        readings3.add(new Reading(18, "August", 2020, meter3));
        readings3.add(new Reading(29, "March", 2021, meter3));
        meter3.setReadings(readings3);
        meterRepository.save(meter3);
        client3.setMeter(meter3);
        clientRepository.save(client3);

        Client client4 = new Client();
        client4.setId(4L);
        client4.setFirstName("Naboo");
        client4.setLastName("The Enigma");
        client4.setMeter(new Meter());
        Address address4 = new Address();
        address4.setCity("Manchester");
        address4.setNumber("1");
        address4.setStreet("Naboo's Lane");
        addressRepository.save(address4);
        client4.setAddress(address4);
        Meter meter4 = new Meter();
        List<Reading> readings4 = new ArrayList<>();
        readings4.add(new Reading(62, "July", 2020, meter4));
        readings4.add(new Reading(26, "March", 2021, meter4));
        readings4.add(new Reading(42, "May", 2021, meter4));
        readings4.add(new Reading(13, "September", 2020, meter4));
        readings4.add(new Reading(21, "October", 2021, meter4));
        meter4.setReadings(readings4);
        meterRepository.save(meter4);
        client4.setMeter(meter4);
        clientRepository.save(client4);

        Client client5 = new Client();
        client5.setId(5L);
        client5.setFirstName("Bollo");
        client5.setLastName("The Gorilla");
        client5.setMeter(new Meter());
        Address address5 = new Address();
        address5.setCity("Dalston");
        address5.setNumber("99");
        address5.setStreet("Jungle Drive");
        addressRepository.save(address5);
        client5.setAddress(address5);
        Meter meter5 = new Meter();
        List<Reading> readings5 = new ArrayList<>();
        readings5.add(new Reading(15, "January", 2021, meter5));
        readings5.add(new Reading(22, "May", 2020, meter5));
        readings5.add(new Reading(13, "July", 2021, meter5));
        readings5.add(new Reading(19, "April", 2021, meter5));
        readings5.add(new Reading(34, "November", 2021, meter5));
        meter5.setReadings(readings5);
        meterRepository.save(meter5);
        client5.setMeter(meter5);
        clientRepository.save(client5);
    }
}
