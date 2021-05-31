package com.bosko.typeqastassignment.api.v1.mapper;

import com.bosko.typeqastassignment.api.v1.dto.AddressDTO;
import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.api.v1.dto.MeterDTO;
import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.entity.Address;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * First, I tried using MapStruct Mapper, but had problems with it.
 * Sometimes it generated mapper class correct, other times all mappings were null(or empty).
 * That is why I added custom mapper class to handle all the mappings back and forth.
 */
@Component
public class Mapper {

    public ClientDTO transformClientToDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setAddressDTO(transformAddressToDTO(client.getAddress()));
        clientDTO.setMeterDTO(transformMeterToDTO(client.getMeter()));

        return clientDTO;
    }

    public Client transformClientDTOToEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        Client client = new Client();

        client.setId(clientDTO.getId());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setAddress(transformAddressDTOToEntity(clientDTO.getAddressDTO()));
        client.setMeter(transformMeterDTOToEntity(clientDTO.getMeterDTO()));

        return client;
    }

    public ReadingDTO transformToReadingDTO(Reading reading) {
        if (reading == null) {
            return null;
        }

        ReadingDTO readingDTO = new ReadingDTO();

        readingDTO.setId(reading.getId());
        readingDTO.setValue(reading.getValue());
        readingDTO.setMonth(reading.getMonth());
        readingDTO.setYear(String.valueOf(reading.getYear()));
        readingDTO.setMeterDTOId(reading.getMeter().getId());

        return readingDTO;
    }

    public Reading transformReadingDTOToEntity(ReadingDTO readingDTO) {
        if (readingDTO == null) {
            return null;
        }

        Reading reading = new Reading();

        reading.setId(readingDTO.getId());
        reading.setValue(readingDTO.getValue());
        reading.setMonth(readingDTO.getMonth());
        if (readingDTO.getYear() != null) {
            reading.setYear(Integer.parseInt(readingDTO.getYear()));
        }
        reading.setMeter(transformMeterDTOToEntity(readingDTO.getMeterDTO()));

        return reading;
    }

    public AddressDTO transformAddressToDTO(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setStreet(address.getStreet());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setCity(address.getCity());

        return addressDTO;
    }

    public Address transformAddressDTOToEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }

        Address address = new Address();

        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setCity(addressDTO.getCity());

        return address;
    }

    public MeterDTO transformMeterToDTO(Meter meter) {
        if (meter == null) {
            return null;
        }

        MeterDTO meterDTO = new MeterDTO();
        meterDTO.setId(meter.getId());
        meterDTO.setReadingDTOS(transformReadingDTOsListToEntityList(meter.getReadings()));


        return meterDTO;
    }

    public Meter transformMeterDTOToEntity(MeterDTO meterDTO) {
        if (meterDTO == null) {
            return null;
        }

        Meter meter = new Meter();

        meter.setId(meterDTO.getId());
        meter.setReadings(transformReadingsToDTOList(meterDTO.getReadingDTOS()));

        return meter;
    }

    public List<Reading> transformReadingsToDTOList(List<ReadingDTO> readingDTOs) {

        List<Reading> readings = new ArrayList<>();
        for(ReadingDTO readingDTO : readingDTOs) {
            readings.add(transformReadingDTOToEntity(readingDTO));
        }
        return readings;
    }

    public List<ReadingDTO> transformReadingDTOsListToEntityList(List<Reading> readings) {

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        for(Reading reading : readings) {
            readingDTOS.add(transformToReadingDTO(reading));
        }
        return readingDTOS;
    }

}
