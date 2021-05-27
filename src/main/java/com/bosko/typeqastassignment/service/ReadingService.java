package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.ReadingDTO;

import java.util.List;

public interface ReadingService {

    List<ReadingDTO> getAllReadingsForClientId(Long id);

    ReadingDTO getTotalOfReadingsForClientId(Long id, int year);

    List<ReadingDTO> getAllReadingsPerClientPerYear(Long id, int year);

    ReadingDTO getReadingForClientIdForMonth(Long id, String month, int year);

    ReadingDTO createNewReading(Long id, ReadingDTO readingDTO);

    ReadingDTO updateReading(Long id, ReadingDTO readingDTO);

    void deleteReading(Long id, String month, int year);

}
