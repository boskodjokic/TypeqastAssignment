package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Reading repository for handling basic operation with database.
 */

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    /**
     * Method for getting list of readings from the database with below parameter
     * @param id client id for querying the database
     * @return list of readings
     */
    @Query(value = "SELECT ID, MONTH, YEAR, VALUE FROM READING \n" +
            "WHERE METER_ID IN\n" +
            "(SELECT METER_ID FROM CLIENT WHERE ID = :id)", nativeQuery = true)
    List<Reading> getAllReadingsForClientId(@Param("id") Long id);

    /**
     * Method for getting reading id from the database with below parameters
     * @param year year of reading
     * @param month month of reading
     * @param id meterId of reading
     * @return reading id in the database
     */
    @Query(value = "SELECT ID FROM READING WHERE \n" +
            "YEAR = :year AND MONTH = :month AND METER_ID = :meter_id", nativeQuery = true)
    Long getReadingId(@Param("year") int year, @Param("month") String month, @Param("meter_id") Long id);

    /**
     * Method for getting meter id from the database with below parameters
     * @param id client id which corresponds with the meter in database
     * @return meter id in the database
     */
    @Query(value = "SELECT METER_ID FROM CLIENT WHERE \n" +
            "ID = :client_id", nativeQuery = true)
    Long getMeterId(@Param("client_id") Long id);
}
