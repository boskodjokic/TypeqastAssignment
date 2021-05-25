package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
}
