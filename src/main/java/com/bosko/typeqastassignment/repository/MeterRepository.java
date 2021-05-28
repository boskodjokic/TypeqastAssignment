package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Meter repository for handling basic operation with database.
 */

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
}
