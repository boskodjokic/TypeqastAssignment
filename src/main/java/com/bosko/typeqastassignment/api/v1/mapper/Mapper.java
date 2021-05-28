package com.bosko.typeqastassignment.api.v1.mapper;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.stereotype.Component;

/**
 * First, I tried using MapStruct Mapper, but had problems with it.
 * Sometimes it generated mapper class correct, other times all mappings were null(or empty).
 * That is why I added custom mapper class to handle all the mappings back and forth.
 */
@Component
public class Mapper {

    public ClientDTO transformClientToDTO(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId( client.getId() );
        clientDTO.setFirstName( client.getFirstName() );
        clientDTO.setLastName( client.getLastName() );
        clientDTO.setAddress( client.getAddress() );
        clientDTO.setMeter( client.getMeter() );

        return clientDTO;
    }

    public Client transformClientDTOToEntity(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientDTO.getId() );
        client.setFirstName( clientDTO.getFirstName() );
        client.setLastName( clientDTO.getLastName() );
        client.setAddress( clientDTO.getAddress() );
        client.setMeter( clientDTO.getMeter() );

        return client;
    }

    public ReadingDTO transformToReadingDTO(Reading reading) {
        if ( reading == null ) {
            return null;
        }

        ReadingDTO readingDTO = new ReadingDTO();

        readingDTO.setId( reading.getId() );
        readingDTO.setValue( reading.getValue() );
        readingDTO.setMonth( reading.getMonth() );
        readingDTO.setYear( String.valueOf( reading.getYear() ) );
        readingDTO.setMeter( reading.getMeter() );

        return readingDTO;
    }

    public Reading transformReadingDTOToEntity(ReadingDTO readingDTO) {
        if ( readingDTO == null ) {
            return null;
        }

        Reading reading = new Reading();

        reading.setId( readingDTO.getId() );
        reading.setValue( readingDTO.getValue() );
        reading.setMonth( readingDTO.getMonth() );
        if ( readingDTO.getYear() != null ) {
            reading.setYear( Integer.parseInt( readingDTO.getYear() ) );
        }
        reading.setMeter( readingDTO.getMeter() );

        return reading;
    }
}
