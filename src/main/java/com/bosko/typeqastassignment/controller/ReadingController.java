package com.bosko.typeqastassignment.controller;

import com.bosko.typeqastassignment.dto.UserReadingDTO;
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

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserReadingDTO> getReadingsByClientId(@PathVariable Long id) {
        return readingService.getAllReadingsForClientId(id);
    }

    @GetMapping("total/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserReadingDTO getTotalReadingsByClientId(@PathVariable Long id) {
        return readingService.getTotalOfReadingsForClientId(id);
    }
}
