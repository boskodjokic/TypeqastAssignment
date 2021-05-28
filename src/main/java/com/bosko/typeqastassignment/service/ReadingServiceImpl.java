package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.dto.ReadingDTO;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.entity.Reading;
import com.bosko.typeqastassignment.exceptions.BadRequestException;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.mapper.MapStructMapper;
import com.bosko.typeqastassignment.repository.ClientRepository;
import com.bosko.typeqastassignment.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ReadingDTO> getAllReadingsForClientId(Long clientId) {
        checkIfClientExists(clientId);
        return readingRepository.getAllReadingsForClientId(clientId).stream().map(mapStructMapper::transformToReadingDTO).collect(Collectors.toList());
    }

    @Override
    public ReadingDTO getTotalOfReadingsForClientId(Long clientId, int year) {
        checkIfClientExists(clientId);
        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        int value = 0;
        for (ReadingDTO reading : readings) {
            if (reading.getYear().equals(String.valueOf(year))) {
                value += reading.getValue();
            }
        }
        ReadingDTO totalReadings = new ReadingDTO();
        totalReadings.setValue(value);
        totalReadings.setMonth("All months");
        totalReadings.setYear(String.valueOf(year));
        totalReadings.setMeter(totalReadings.getMeter());

        return totalReadings;
    }

    @Override
    public List<ReadingDTO> getAllReadingsPerClientPerYear(Long clientId, int year) {
        checkIfClientExists(clientId);
        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        List<ReadingDTO> readingsPerYear = new ArrayList<>();
        for (ReadingDTO reading : readings) {
            if (reading.getYear().equals(String.valueOf(year))) {
                ReadingDTO totalReadings = new ReadingDTO();
                totalReadings.setValue(reading.getValue());
                totalReadings.setMonth(reading.getMonth());
                totalReadings.setYear(String.valueOf(year));
                totalReadings.setMeter(reading.getMeter());
                readingsPerYear.add(totalReadings);
            }
        }
        return readingsPerYear;
    }

    @Override
    public ReadingDTO getReadingForClientIdForMonth(Long clientId, String month, int year) {
        checkIfClientExists(clientId);
        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        ReadingDTO monthReading = new ReadingDTO();
        for (ReadingDTO reading : readings) {
            if (reading.getMonth().equals(month) && reading.getYear().equals(String.valueOf(year))) {

                monthReading.setValue(reading.getValue());
                monthReading.setMonth(reading.getMonth());
                monthReading.setYear(reading.getYear());
                monthReading.setMeter(reading.getMeter());
            }
        }
        return monthReading;
    }

    @Override
    public ReadingDTO createNewReading(Long clientId, ReadingDTO readingDTO) {
        checkIfClientExists(clientId);

        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        for (ReadingDTO dto : readings) {
            if (readingDTO.getMonth().equals(dto.getMonth()) &&
                    readingDTO.getYear().equals(dto.getYear())) {
                throw new BadRequestException();
            }
        }
        Meter meter = getAllReadingsForClientId(clientId).get(clientId.intValue() - 1).getMeter();
        readingDTO.setMeter(meter);

        Reading reading = mapStructMapper.transformReadingDTOToEntity(readingDTO);


        return mapStructMapper.transformToReadingDTO(readingRepository.save(reading));

    }

    @Override
    public ReadingDTO updateReading(Long clientId, ReadingDTO readingDTO) {

        checkIfClientExists(clientId);
        Meter meter = getAllReadingsForClientId(clientId).get(clientId.intValue() - 1).getMeter();
        Long readingId = readingRepository.getReadingId(Integer.parseInt(readingDTO.getYear()), readingDTO.getMonth(), meter.getId());
        if (readingId == null) {
            throw new ResourceNotFoundException();
        }
        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        for (int i = 0; i < readings.size(); i++) {

            if (!readingDTO.getMonth().equals(readings.get(clientId.intValue() - 1).getMonth()) &&
                    !readingDTO.getYear().equals(readings.get(clientId.intValue() - 1).getYear())) {
                throw new BadRequestException();
            } else {
                readingDTO.setId(readingId);
            }
        }
        readingDTO.setMeter(meter);

        Reading reading = mapStructMapper.transformReadingDTOToEntity(readingDTO);

        return mapStructMapper.transformToReadingDTO(readingRepository.save(reading));

    }

    @Override
    public void deleteReading(Long clientId, String month, int year) {
        checkIfClientExists(clientId);
        Long readingId = readingRepository.getReadingId(year, month, readingRepository.getMeterId(clientId));
        System.out.println(readingId);
        if (readingId == null) {
            throw new ResourceNotFoundException();
        }
        readingRepository.deleteById(readingId);
    }

    public void checkIfClientExists(Long clientId) {
        if (!clientRepository.findById(clientId).isPresent()) {
            throw new ResourceNotFoundException();
        }
    }

}
