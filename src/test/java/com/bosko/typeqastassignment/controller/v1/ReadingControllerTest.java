package com.bosko.typeqastassignment.controller.v1;

import com.bosko.typeqastassignment.api.v1.dto.ClientDTO;
import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.controller.RestResponseEntityExceptionHandler;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.service.ReadingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReadingControllerTest {

    @Mock
    ReadingService readingService;

    @InjectMocks
    ReadingController readingController;

    MockMvc mockMvc;

    /**
     * Necessary set up before all the tests.
     */
    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(readingController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    void getReadingsByClientId() throws Exception {

        Meter meter = new Meter();
        meter.setId(1L);

        ClientDTO client1 = new ClientDTO();
        client1.setId(1L);
        client1.setFirstName("Bob");
        client1.setLastName("Fossil");
        client1.setMeter(meter);

        ReadingDTO reading = new ReadingDTO();
        reading.setId(1L);
        reading.setMonth("May");
        reading.setValue(12);
        reading.setYear("2021");
        reading.setMeter(meter);

        ReadingDTO reading2 = new ReadingDTO();
        reading2.setId(2L);
        reading2.setMonth("March");
        reading2.setValue(12);
        reading2.setYear("2021");
        reading2.setMeter(meter
        );

        List<ReadingDTO> readings = Arrays.asList(reading, reading2);
        when(readingService.getAllReadingsForClientId(anyLong())).thenReturn(readings);

        mockMvc.perform(get(ReadingController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getTotalReadingsByClientId() {
    }

    @Test
    void getTotalReadingsByClientIdPerYear() {
    }

    @Test
    void getReadingForClientIdForYearAndMonth() {
    }

    @Test
    void createNewReading() {
    }

    @Test
    void updateReading() {
    }

    @Test
    void deleteReading() throws Exception {
        mockMvc.perform(delete(ReadingController.BASE_URL + "/1/March/2021")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(readingService).deleteReading(anyLong(), anyString(), anyInt());
    }

    @Test
    void notFoundException() throws Exception {
        when(readingService.getAllReadingsForClientId(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(ReadingController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}