package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.api.v1.mapper.Mapper;
import com.bosko.typeqastassignment.entity.Client;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.entity.Reading;
import com.bosko.typeqastassignment.repository.ClientRepository;
import com.bosko.typeqastassignment.repository.ReadingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ReadingServiceImplTest {

    private static final Long ID = 1L;
    private static final String FIRST_NAME = "Bob";
    private static final String LAST_NAME = "Fossil";

    ReadingService readingService;

    @Mock
    ReadingRepository readingRepository;

    @InjectMocks
    Mapper mappper;

    @Mock
    ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        readingService = new ReadingServiceImpl(mappper, readingRepository, clientRepository);
    }

    @Test
    void getAllReadingsForClientId() {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((getClient())));
        when(readingRepository.getAllReadingsForClientId(ID)).thenReturn(getReadings());

        List<ReadingDTO> readingDTOs = readingService.getAllReadingsForClientId(ID);

        assertEquals(2, readingDTOs.size());
        assertEquals(15, readingDTOs.get(0).getValue());
        assertEquals("May", readingDTOs.get(0).getMonth());
        assertEquals("2021", readingDTOs.get(0).getYear());
    }

    @Test
    void getTotalOfReadingsForClientId() {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((getClient())));
        when(readingRepository.getAllReadingsForClientId(ID)).thenReturn(getReadings());

        List<ReadingDTO> readingDTOs = readingService.getAllReadingsForClientId(ID);

        ReadingDTO totalReading = readingService.getTotalOfReadingsForClientId(ID, 2021);
        totalReading.setValue(readingDTOs.get(0).getValue() + readingDTOs.get(1).getValue());
        totalReading.setMonth("All months");
        totalReading.setYear(readingDTOs.get(0).getYear());

        assertEquals(40, totalReading.getValue());
        assertEquals("All months", totalReading.getMonth());
        assertEquals("2021", totalReading.getYear());
    }

    @Test
    void getAllReadingsPerClientPerYear() {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((getClient())));
        when(readingRepository.getAllReadingsForClientId(ID)).thenReturn(getReadings());

        List<ReadingDTO> readingDTOs = readingService.getAllReadingsPerClientPerYear(ID, 2021);

        assertEquals(2, readingDTOs.size());
        assertEquals(15, readingDTOs.get(0).getValue());
        assertEquals("May", readingDTOs.get(0).getMonth());
        assertEquals("2021", readingDTOs.get(0).getYear());
    }

    @Test
    void getReadingForClientIdForMonth() {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((getClient())));
        when(readingRepository.getAllReadingsForClientId(ID)).thenReturn(getReadings());

        List<ReadingDTO> readingDTOs = readingService.getAllReadingsForClientId(ID);

        ReadingDTO monthReading = readingService.getReadingForClientIdForMonth(ID, "May", 2021);
        monthReading.setValue(readingDTOs.get(0).getValue());
        monthReading.setMonth(readingDTOs.get(0).getMonth());
        monthReading.setYear(readingDTOs.get(0).getYear());

        assertEquals(15, monthReading.getValue());
        assertEquals("May", monthReading.getMonth());
        assertEquals("2021", monthReading.getYear());
    }

    @Test
    void createNewReading() {

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setValue(16);
        readingDTO.setMonth("July");
        readingDTO.setYear("2021");

        Reading savedReading = new Reading();

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((getClient())));
        when(readingRepository.getAllReadingsForClientId(ID)).thenReturn(getReadings());

        savedReading.setValue(readingDTO.getValue());
        savedReading.setMonth(readingDTO.getMonth());
        savedReading.setYear(Integer.parseInt(readingDTO.getYear()));

        when(readingRepository.save(any(Reading.class))).thenReturn(savedReading);

        ReadingDTO savedDTO = readingService.createNewReading(anyLong(), readingDTO);

        assertEquals(16, savedDTO.getValue());
        assertEquals("July", savedDTO.getMonth());
        assertEquals("2021", savedDTO.getYear());
    }

    @Test
    void updateReading() {

        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setValue(16);
        readingDTO.setMonth("July");
        readingDTO.setYear("2021");

        Reading savedReading = new Reading();

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of((getClient())));
        when(readingRepository.getAllReadingsForClientId(ID)).thenReturn(getReadings());
        when(readingRepository.getReadingId(2021, "July", ID)).thenReturn(ID);

        savedReading.setValue(readingDTO.getValue());
        savedReading.setMonth(readingDTO.getMonth());
        savedReading.setYear(Integer.parseInt(readingDTO.getYear()));

        when(readingRepository.save(any(Reading.class))).thenReturn(savedReading);

        ReadingDTO savedDTO = readingService.updateReading(anyLong(), readingDTO);

        assertEquals(16, savedDTO.getValue());
        assertEquals("July", savedDTO.getMonth());
        assertEquals("2021", savedDTO.getYear());
    }

    @Test
    void deleteReading() {
        readingRepository.deleteById(ID);
        verify(readingRepository, times(1)).deleteById(anyLong());
    }

    public Client getClient() {
        Client client = new Client();
        client.setId(ID);
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        Meter meter = new Meter();
        meter.setId(ID);
        client.setMeter(meter);
        return client;
    }

    public List<Reading> getReadings() {
        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading();
        reading.setId(ID);
        reading.setValue(15);
        reading.setMonth("May");
        reading.setYear(2021);

        Reading reading2 = new Reading();
        reading2.setId(2L);
        reading2.setValue(25);
        reading2.setMonth("June");
        reading2.setYear(2021);

        readings.add(reading);
        readings.add(reading2);

        return readings;
    }
}