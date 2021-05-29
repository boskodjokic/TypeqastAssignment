package com.bosko.typeqastassignment.controller.v1;

import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.controller.RestResponseEntityExceptionHandler;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.service.ReadingService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    /**
     * First, we are making 2 DTO objects and putting them in the list.
     * When we call readingService.getAllReadingsForClientId() that returns our list of readings.
     * Than mockMvc gets the BASE_URL/1 and performs get method that accepts JSON format.
     * We expect the status is OK and that we have 2 objects in our list, and we also check are months corresponding to the ones we entered.
     */
    @Test
    void getReadingsByClientId() throws Exception {

        List<ReadingDTO> readings = setReadings();
        when(readingService.getAllReadingsForClientId(anyLong())).thenReturn(readings);

        mockMvc.perform(get(ReadingController.BASE_URL + "/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].month", equalTo("May")))
                .andExpect(jsonPath("$.[1].month", equalTo("March")));
    }

    /**
     * First, we are making 2 DTO objects and putting them in the list.
     * Then, we are adding values to the new DTO object.
     * When we call readingService.getTotalOfReadingsForClientId() that returns our totalReading.
     * Than mockMvc gets the BASE_URL/total/1/2021 and performs get method that accepts JSON format.
     * We expect the status is OK and we check are months corresponding to the ones we entered.
     */
    @Test
    void getTotalReadingsByClientId() throws Exception {

        List<ReadingDTO> readings = setReadings();
        int totalValue = 0;
        for (ReadingDTO reading : readings) {
            totalValue += reading.getValue();
        }

        ReadingDTO totalReading = new ReadingDTO();
        totalReading.setValue(totalValue);
        totalReading.setMonth("All months");
        totalReading.setYear("2021");

        when(readingService.getTotalOfReadingsForClientId(anyLong(), anyInt())).thenReturn(totalReading);

        mockMvc.perform(get(ReadingController.BASE_URL + "/total/1/2021")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", equalTo(24)))
                .andExpect(jsonPath("$.month", equalTo("All months")))
                .andExpect(jsonPath("$.year", equalTo("2021")));
    }

    /**
     * First, we are making 2 DTO objects and putting them in the list.
     * When we call readingService.getAllReadingsPerClientPerYear() that returns our list of readings.
     * Than mockMvc gets the BASE_URL/1/2021 and performs get method that accepts JSON format.
     * We expect the status is OK and that we have 2 objects, and we also check are months corresponding to the ones we entered.
     */
    @Test
    void getAllReadingsByClientIdPerYear() throws Exception {

        List<ReadingDTO> readings = setReadings();

        when(readingService.getAllReadingsPerClientPerYear(anyLong(), anyInt())).thenReturn(readings);

        mockMvc.perform(get(ReadingController.BASE_URL + "/1/2021")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].month", equalTo("May")))
                .andExpect(jsonPath("$.[1].month", equalTo("March")));
    }

    /**
     * First, we are making 2 DTO objects and putting them in the list.
     * When we call readingService.getReadingForClientIdForMonth() that returns first entry in our list of readings.
     * Than mockMvc gets the BASE_URL/1/May/2021 and performs get method that accepts JSON format.
     * We expect the status is OK and we also check are values corresponding to the ones we entered.
     */
    @Test
    void getReadingForClientIdForYearAndMonth() throws Exception {

        List<ReadingDTO> readings = setReadings();

        when(readingService.getReadingForClientIdForMonth(anyLong(), anyString(), anyInt())).thenReturn(readings.get(0));

        mockMvc.perform(get(ReadingController.BASE_URL + "/1/May/2021")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.month", equalTo("May")))
                .andExpect(jsonPath("$.year", equalTo("2021")));
    }

    /**
     * First, we are making a DTO object.
     * After that we are making new DTO object that will hold data from the first object.
     * When we call readingService.createNewReading() with first object, that returns second object.
     * Than mockMvc calls post on the BASE_URL/1 that accepts JSON format.
     * We expect the status is Created and that values are corresponding to the ones we entered.
     */
    @Test
    void createNewReading() throws Exception {

        ReadingDTO reading = new ReadingDTO();
        reading.setMonth("May");
        reading.setValue(12);
        reading.setYear("2021");

        ReadingDTO newReading = new ReadingDTO();
        newReading.setMonth(reading.getMonth());
        newReading.setValue(reading.getValue());
        newReading.setYear(reading.getYear());

        when(readingService.createNewReading(anyLong(), any(ReadingDTO.class))).thenReturn(newReading);

        mockMvc.perform(post(ReadingController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().valueToTree(newReading).toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.month", equalTo("May")))
                .andExpect(jsonPath("$.year", equalTo("2021")));

    }

    /**
     * First, we are making a DTO object.
     * After that we are making new DTO object that will hold data from the first object.
     * When we call readingService.updateReading() with first object, that returns second object.
     * Than mockMvc calls put on the BASE_URL/1 that accepts JSON format.
     * We expect the status is OK and that values are corresponding to the ones we entered.
     */
    @Test
    void updateReading() throws Exception {

        ReadingDTO reading = new ReadingDTO();
        reading.setMonth("May");
        reading.setValue(12);
        reading.setYear("2021");

        ReadingDTO newReading = new ReadingDTO();
        newReading.setMonth(reading.getMonth());
        newReading.setValue(25);
        newReading.setYear(reading.getYear());

        when(readingService.updateReading(anyLong(), any(ReadingDTO.class))).thenReturn(newReading);

        mockMvc.perform(put(ReadingController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().valueToTree(newReading).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", equalTo(25)))
                .andExpect(jsonPath("$.month", equalTo("May")))
                .andExpect(jsonPath("$.year", equalTo("2021")));
    }

    /**
     * We call readingService.deleteReading() on BASE_URL/1/March/2021.
     * We expect the status is OK and we are verifying that readingService gets called with method deleteReading.
     */
    @Test
    void deleteReading() throws Exception {
        mockMvc.perform(delete(ReadingController.BASE_URL + "/1/March/2021").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(readingService).deleteReading(anyLong(), anyString(), anyInt());
    }

    /**
     *We are calling getAllReadingsForClientId() with some non-existing ID and expecting that the exception is thrown.
     */
    @Test
    void notFoundException() throws Exception {
        when(readingService.getAllReadingsForClientId(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(ReadingController.BASE_URL + "/321").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * Helper method for creating 2 DTO objects and putting them in a list.
     */
    public List<ReadingDTO> setReadings() {
        ReadingDTO reading = new ReadingDTO();
        reading.setId(1L);
        reading.setMonth("May");
        reading.setValue(12);
        reading.setYear("2021");

        ReadingDTO reading2 = new ReadingDTO();
        reading2.setId(2L);
        reading2.setMonth("March");
        reading2.setValue(12);
        reading2.setYear("2021");

        return Arrays.asList(reading, reading2);
    }
}