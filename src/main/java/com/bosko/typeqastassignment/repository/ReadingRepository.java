package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.dto.UserReading;
import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    @Query(value = "SELECT reading.create_date, reading.value, reading.meter_id, client.first_name, client.last_name\n" +
            "FROM reading\n" +
            "LEFT JOIN meter ON reading.meter_id = meter.id\n" +
            "LEFT JOIN client ON client.meter_id = meter.id WHERE client.id = :id", nativeQuery = true)
    List<UserReading> getAllReadingsForClientId(@Param("id") Long id);

    @Query(value = "SELECT YEAR(reading.create_date), reading.meter_id, client.first_name, client.last_name, SUM(reading.value) as value\n" +
            "FROM reading\n" +
            "LEFT JOIN meter ON reading.meter_id = meter.id\n" +
            "LEFT JOIN client ON client.meter_id = meter.id WHERE client.id = :id\n" +
            "GROUP BY YEAR(reading.create_date)", nativeQuery = true)
    Optional<UserReading> getTotalValueOfReadingsForClientId(@Param("id") Long id);
}
