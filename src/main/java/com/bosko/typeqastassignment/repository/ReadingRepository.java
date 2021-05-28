package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    @Query(value = "SELECT ID, MONTH, YEAR, VALUE FROM READING \n" +
            "WHERE METER_ID IN\n" +
            "(SELECT METER_ID FROM CLIENT WHERE ID = :id)", nativeQuery = true)
    List<Reading> getAllReadingsForClientId(@Param("id") Long id);

    @Query(value = "SELECT ID FROM READING WHERE \n" +
            "YEAR = :year AND MONTH = :month AND METER_ID = :meter_id", nativeQuery = true)
    Long getReadingId(@Param("year") int year, @Param("month") String month, @Param("meter_id") Long id);

    @Query(value = "SELECT METER_ID FROM CLIENT WHERE \n" +
            "ID = :client_id", nativeQuery = true)
    Long getMeterId(@Param("client_id") Long id);
}
