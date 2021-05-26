package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.UserReadingDTO;

import java.util.List;

public interface ReadingService {

    List<UserReadingDTO> getAllReadingsForClientId(Long id);

    UserReadingDTO getTotalOfReadingsForClientId(Long id);

}
