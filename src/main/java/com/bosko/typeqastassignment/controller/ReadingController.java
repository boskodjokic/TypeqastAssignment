package com.bosko.typeqastassignment.controller;

import com.bosko.typeqastassignment.dto.ReadingDTO;
import com.bosko.typeqastassignment.mapper.MapStructMapper;
import com.bosko.typeqastassignment.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ReadingController.BASE_URL)
public class ReadingController {

    public static final String BASE_URL = "/api/v1/readings";

    @Autowired
    private ReadingService readingService;

    @Autowired
    private MapStructMapper mapStructMapper;

    @GetMapping("{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadingDTO> getReadingsByClientId(@PathVariable Long clientId) {
        return readingService.getAllReadingsForClientId(clientId);
    }

    @GetMapping("/total/{clientId}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public ReadingDTO getTotalReadingsByClientId(@PathVariable Long clientId, @PathVariable int year) {
        return readingService.getTotalOfReadingsForClientId(clientId, year);
    }

    @GetMapping("{clientId}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadingDTO> getTotalReadingsByClientIdPerYear(@PathVariable Long clientId, @PathVariable int year) {
        return readingService.getAllReadingsPerClientPerYear(clientId, year);
    }

    @GetMapping("{clientId}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public ReadingDTO getReadingForClientIdForYearAndMonth(@PathVariable Long clientId, @PathVariable String month, @PathVariable int year) {
        return readingService.getReadingForClientIdForMonth(clientId, month, year);
    }

    @PostMapping("{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReadingDTO createNewReading(@PathVariable Long clientId, @RequestBody ReadingDTO readingDTO) {
        return readingService.createNewReading(clientId, readingDTO);
    }

    @PutMapping("{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ReadingDTO updateReading(@PathVariable Long clientId, @RequestBody ReadingDTO readingDTO) {
        return readingService.updateReading(clientId, readingDTO);
    }

    @DeleteMapping("{clientId}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReading(@PathVariable Long clientId, @PathVariable String month, @PathVariable int year) {
        readingService.deleteReading(clientId, month, year);
    }
}
