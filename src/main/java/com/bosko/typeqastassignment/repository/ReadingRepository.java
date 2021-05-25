package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
}
