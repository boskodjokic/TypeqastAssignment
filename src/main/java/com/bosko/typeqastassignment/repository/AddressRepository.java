package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Address repository for handling basic operation with database.
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
