package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.UserReading;
import com.bosko.typeqastassignment.dto.UserReadingDTO;
import com.bosko.typeqastassignment.mapper.MapStructMapper;
import com.bosko.typeqastassignment.repository.ClientRepository;
import com.bosko.typeqastassignment.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReadingService readingService;


    @Override
    public List<UserReadingDTO> getAllReadingsForClientId(Long clientId) {
        if (!clientRepository.findById(clientId).isPresent()) {
            throw new ResourceNotFoundException();
        }
        List<UserReading> readings = readingRepository.getAllReadingsForClientId(clientId);
        return readingRepository.getAllReadingsForClientId(clientId).stream().map(mapStructMapper::transformUserReadingToDTO).collect(Collectors.toList());
    }

    @Override
    public UserReadingDTO getTotalOfReadingsForClientId(Long clientId) {
        Optional<UserReading> reading = readingRepository.getTotalValueOfReadingsForClientId(clientId);
        if (!clientRepository.findById(clientId).isPresent()) {
            throw new ResourceNotFoundException();
        }
        return mapStructMapper.transformUserReadingToDTO(reading.get());
    }
}
