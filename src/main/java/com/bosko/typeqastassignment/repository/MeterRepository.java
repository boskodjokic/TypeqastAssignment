package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
}
