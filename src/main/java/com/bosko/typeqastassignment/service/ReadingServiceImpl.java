package com.bosko.typeqastassignment.service;

import com.bosko.typeqastassignment.api.v1.dto.ReadingDTO;
import com.bosko.typeqastassignment.api.v1.mapper.Mapper;
import com.bosko.typeqastassignment.entity.Meter;
import com.bosko.typeqastassignment.entity.Reading;
import com.bosko.typeqastassignment.exceptions.ResourceAlreadyExistsException;
import com.bosko.typeqastassignment.exceptions.ResourceNotFoundException;
import com.bosko.typeqastassignment.repository.ClientRepository;
import com.bosko.typeqastassignment.repository.ReadingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadingServiceImpl implements ReadingService {

    private final Mapper mapper;

    private final ReadingRepository readingRepository;

    private final ClientRepository clientRepository;

    public ReadingServiceImpl(Mapper mapper, ReadingRepository readingRepository, ClientRepository clientRepository) {
        this.mapper = mapper;
        this.readingRepository = readingRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Method for getting all readings for a specified client id
     * First, we check is the client present in the database. If not, error is shown to user.
     * @param clientId is passed for checking
     * @return list of readings is returned from the database to user.
     */
    @Override
    public List<ReadingDTO> getAllReadingsForClientId(Long clientId) {
        checkIfClientExists(clientId);
        return readingRepository.getAllReadingsForClientId(clientId).stream().map(mapper::transformToReadingDTO).collect(Collectors.toList());
    }

    /**
     *Method for retrieving total value of readings for client per specified year.
     * @param clientId is passed for checking is the client present in the database, if not, error is shown to user.
     * @param year is also passed as a parameter for which we calculate total value of readings.
     * @return ReadingDTO is returned to user.
     */
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

    /**
     * Method for getting a list of readings for a client id for whole year.
     * We are checking all the readings for a clientId and checking is it for a specified year. If yes, we are adding those readings to the list.
     * @param clientId is passed for checking is the client present in the database, if not, error is shown to user.
     * @param year is also passed as a parameter for which we retrieve the readings from a database.
     * @return ReadingDTO list is returned to user.
     */
    @Override
    public List<ReadingDTO> getAllReadingsPerClientPerYear(Long clientId, int year) {
        checkIfClientExists(clientId);
        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        List<ReadingDTO> readingsPerYear = new ArrayList<>();
        for (ReadingDTO reading : readings) {
            if (reading.getYear().equals(String.valueOf(year))) {
                ReadingDTO totalReadings;
                totalReadings = reading;
                readingsPerYear.add(totalReadings);
            }
        }
        return readingsPerYear;
    }

    /**
     * Method for getting a reading for a specified clientId, month and year.
     * We are checking is there a reading for a specified user in the database for month and year provided.
     * If yes, we are taking that reading to show it to the user together with month and year.
     * If they are not, error is shown to the user that specified resource is not available.
     * @param clientId is passed for checking is the client present in the database, if not, error is shown to user.
     * @param month is passed as a parameter for checking is the month in the database.
     * @param year is passed as a parameter for checking is the year in the database.
     * @return ReadingDTO object is returned to the user.
     */
    @Override
    public ReadingDTO getReadingForClientIdForMonth(Long clientId, String month, int year) {
        checkIfClientExists(clientId);
        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        ReadingDTO monthReading = new ReadingDTO();
        for (ReadingDTO reading : readings) {
            if (reading.getMonth().equals(month) && reading.getYear().equals(String.valueOf(year))) {
                monthReading = reading;
            }
        }
        if (monthReading.getMonth() == null && monthReading.getYear() == null) {
            throw new ResourceNotFoundException();
        } else {
            return monthReading;
        }
    }

    /**
     * Method for creating a new reading.
     * @param clientId client id is passed for checking is the client already in a database, if not, error is shown to the user.
     * @param readingDTO with all necessary fields is passed.
     * First, we are checking is there already that month/year combination in the database. If yes, error is shown to the user and entry is not saved.
     * @return ReadingDTO with inserted values is shown back to the user if everything is OK.
     */
    @Override
    public ReadingDTO createNewReading(Long clientId, ReadingDTO readingDTO) {
        checkIfClientExists(clientId);

        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);
        for (ReadingDTO dto : readings) {
            if (readingDTO.getMonth().equals(dto.getMonth()) &&
                    readingDTO.getYear().equals(dto.getYear())) {
                throw new ResourceAlreadyExistsException();
            }
        }
        readingDTO.setMeter(clientRepository.findById(clientId).get().getMeter());

        Reading reading = mapper.transformReadingDTOToEntity(readingDTO);

        return mapper.transformToReadingDTO(readingRepository.save(reading));

    }

    /**
     * Method for updating a reading.
     * @param clientId is passed for checking is the client already in a database, if not, error is shown to the user.
     * @param readingDTO with all necessary fields is passed.
     * First we are checking is there reading with specified year/month.
     * Only if they are present, value can be updated.
     * If the year and month provided are not in the database, error is show to the user.
     * @return ReadingDTO with inserted values is shown back to the user if everything is OK.
     */
    @Override
    public ReadingDTO updateReading(Long clientId, ReadingDTO readingDTO) {

        checkIfClientExists(clientId);
        Meter meter = clientRepository.findById(clientId).get().getMeter();
        Long readingId = readingRepository.getReadingId(Integer.parseInt(readingDTO.getYear()), readingDTO.getMonth(), meter.getId());
        if (readingId == null) {
            throw new ResourceNotFoundException();
        }

        List<ReadingDTO> readings = getAllReadingsForClientId(clientId);

        for (ReadingDTO dto : readings) {
            if (!readingDTO.getMonth().equals(dto.getMonth()) &&
                    !readingDTO.getYear().equals(dto.getYear())) {
                throw new ResourceAlreadyExistsException();
            }
            else {
                readingDTO.setId(readingId);
            }
        }

        readingDTO.setMeter(meter);

        Reading reading = mapper.transformReadingDTOToEntity(readingDTO);

        return mapper.transformToReadingDTO(readingRepository.save(reading));

    }

    /**
     * Method for deleting a reading from a database.
     * @param clientId is passed for checking is the client already in a database, if not, error is shown to the user.
     * @param month for which we are checking is in the database.
     * @param year for which we are checking is in the database.
     * We are checking is the reading id in the database for specified parameters. if not, error is shown to the user.
     * If everything is OK, reading is deleted from a database.
     */
    @Override
    public void deleteReading(Long clientId, String month, int year) {
        checkIfClientExists(clientId);
        Long meterId = readingRepository.getMeterId(clientId);
        Long readingId = readingRepository.getReadingId(year, month, meterId);
        if (readingId == null) {
            throw new ResourceNotFoundException();

        }
        readingRepository.deleteById(readingId);
    }

    /**
     * Method for checking is the user in the database.
     * @param clientId is passed for checking is the client already in a database, if not, error is shown to the user.
     */
    public void checkIfClientExists(Long clientId) {
        if (!clientRepository.findById(clientId).isPresent()) {
            throw new ResourceNotFoundException();
        }
    }

}
