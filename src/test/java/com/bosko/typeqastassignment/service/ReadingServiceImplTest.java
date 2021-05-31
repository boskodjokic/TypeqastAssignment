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
    Mapper mapper;

    @Mock
    ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        readingService = new ReadingServiceImpl(mapper, readingRepository, clientRepository);
    }

    /**
     * When we call clientRepository.findById() that returns our client object form helper method below.
     * When we call readingRepository.getAllReadingsForClientId() that returns list of readings from our helper method.
     * We are making new list of readingDTOs and we call readingService.getAllReadingsForClientId();
     * We are asserting that we indeed have 2 items in our list, and that fields are equal to the our first object in list of readings.
     */
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

    /**
     * When we call clientRepository.findById() that returns our client object.
     * When we call readingRepository.getAllReadingsForClientId() that returns list of readings.
     * We are making new list of readingDTOs and we call readingService.getAllReadingsForClientId();
     * Also, we make new ReadingDTO object when calling readingService.getTotalOfReadingsForClientId().
     * After that, we are setting fields for our new DTO object and asserting that fields are equal for it.
     */
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

    /**
     * When we call clientRepository.findById() that returns our client object form helper method below.
     * When we call readingRepository.getAllReadingsForClientId() that returns list of readings from our helper method.
     * We are making new list of readingDTOs and we call readingService.getAllReadingsPerClientPerYear();
     * We are asserting that we indeed have 2 items in our list, and that fields are equal to the our first object in list of readings.
     */
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

    /**
     * When we call clientRepository.findById() that returns our client object.
     * When we call readingRepository.getAllReadingsForClientId() that returns list of readings.
     * We are making new list of readingDTOs and we call readingService.getAllReadingsForClientId();
     * Also, we make new ReadingDTO object when calling readingService.getReadingForClientIdForMonth().
     * After that, we are setting fields for our new DTO object and asserting that fields are equal for it.
     */
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

    /**
     * First, we are making new DTO object and setting fields for it.
     * Also, we are creating new Reading object, and setting fields from the DTO.
     * When we call clientRepository.findById() that returns our client object form helper method below.
     * When we call readingRepository.getAllReadingsForClientId() that returns list of readings from our helper method.
     * We are making new DTO when we call readingService.createNewReading();
     * We are asserting that fields are equal to our savedDTO object.
     */
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
        savedReading.setMeter(new Meter());

        when(readingRepository.save(any(Reading.class))).thenReturn(savedReading);

        ReadingDTO savedDTO = readingService.createNewReading(anyLong(), readingDTO);

        assertEquals(16, savedDTO.getValue());
        assertEquals("July", savedDTO.getMonth());
        assertEquals("2021", savedDTO.getYear());
    }

    /**
     * First, we are making new DTO object and setting fields for it.
     * Also, we are creating new Reading object, and setting fields from the DTO.
     * When we call clientRepository.findById() that returns our client object form helper method below.
     * When we call readingRepository.getAllReadingsForClientId() that returns list of readings from our helper method.
     * When we call readingRepository.getReadingId(), that returns ID of the reading object that should be updated.
     * We are making new DTO when we call readingService.updateReading();
     * We are asserting that fields are equal to our savedDTO object.
     */
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
        savedReading.setMeter(new Meter());

        when(readingRepository.save(any(Reading.class))).thenReturn(savedReading);

        ReadingDTO savedDTO = readingService.updateReading(anyLong(), readingDTO);

        assertEquals(16, savedDTO.getValue());
        assertEquals("July", savedDTO.getMonth());
        assertEquals("2021", savedDTO.getYear());
    }

    /**
     * We are calling readingRepository.deleteById() and verifying that is called exactly one time
     */
    @Test
    void deleteReading() {
        readingRepository.deleteById(ID);
        verify(readingRepository, times(1)).deleteById(anyLong());
    }

    /**
     * Helper method for creating new client object
     * @return new client object for testing purposes.
     */
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

    /**
     * Helper method for creating a list of readings.
     * @return new list of reading objects for testing purposes.
     */
    public List<Reading> getReadings() {
        List<Reading> readings = new ArrayList<>();
        Reading reading = new Reading();
        reading.setId(ID);
        reading.setValue(15);
        reading.setMonth("May");
        reading.setYear(2021);
        Meter meter = new Meter();
        meter.setId(ID);
        reading.setMeter(meter);

        Reading reading2 = new Reading();
        reading2.setId(2L);
        reading2.setValue(25);
        reading2.setMonth("June");
        reading2.setYear(2021);
        reading2.setMeter(meter);

        readings.add(reading);
        readings.add(reading2);

        return readings;
    }
}