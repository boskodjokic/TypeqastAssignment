package com.bosko.typeqastassignment.controller.v1;

import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.service.ReadingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Reading Controller")
@Tag(name = "Reading Controller", description = "This is reading controller")
@RestController
@RequestMapping(ReadingController.BASE_URL)
public class ReadingController {

    public static final String BASE_URL = "/api/v1/readings";

    @Autowired
    private ReadingService readingService;

    @ApiOperation(value = "Returns error, because no ID of a client is specified",
            notes = "Just execute GET method on BASE_URL for readings (/api/v1/readings)")
    @GetMapping
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void incompleteData() {
        throw new ResourceNotFoundException();
    }

    @ApiOperation(value = "Returns a list of all reading for specified client ID",
            notes = "Provide an ID of a client to look for their readings")
    @GetMapping("{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadingDTO> getReadingsByClientId(@PathVariable Long clientId) {
        return readingService.getAllReadingsForClientId(clientId);
    }

    @ApiOperation(value = "This will return list of all reading for specified client ID",
            notes = "Provide an ID of a client and a year to look for total of their readings for a specific year")
    @GetMapping("/total/{clientId}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public ReadingDTO getTotalReadingsByClientId(@PathVariable Long clientId, @PathVariable int year) {
        return readingService.getTotalOfReadingsForClientId(clientId, year);
    }

    @ApiOperation(value = "This will return total value of reading for specified client ID and year",
            notes = "Provide an ID of a client and a year to look for their readings for a specific year")
    @GetMapping("{clientId}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadingDTO> getTotalReadingsByClientIdPerYear(@PathVariable Long clientId, @PathVariable int year) {
        return readingService.getAllReadingsPerClientPerYear(clientId, year);
    }

    @ApiOperation(value = "This will return a reading for specified client ID, month and year",
            notes = "Provide an ID of a client, a year and a month to look for reading with data specified")
    @GetMapping("{clientId}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public ReadingDTO getReadingForClientIdForYearAndMonth(@PathVariable Long clientId, @PathVariable String month, @PathVariable int year) {
        return readingService.getReadingForClientIdForMonth(clientId, month, year);
    }

    @ApiOperation(value = "This will create new reading for specified client ID",
            notes = "Provide ID of a client and input value, month and year")
    @PostMapping("{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReadingDTO createNewReading(@PathVariable Long clientId, @RequestBody ReadingDTO readingDTO) {
        return readingService.createNewReading(clientId, readingDTO);
    }

    @ApiOperation(value = "This will update existing reading for specified client ID",
            notes = "Provide ID of a client and input value, with already existing month and year")
    @PutMapping("{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ReadingDTO updateReading(@PathVariable Long clientId, @RequestBody ReadingDTO readingDTO) {
        return readingService.updateReading(clientId, readingDTO);
    }

    @ApiOperation(value = "This will delete a reading for specified client ID, month and year",
            notes = "Provide an ID of a client, a year and a month to delete reading with data specified")
    @DeleteMapping("{clientId}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReading(@PathVariable Long clientId, @PathVariable String month, @PathVariable int year) {
        readingService.deleteReading(clientId, month, year);
    }
}
