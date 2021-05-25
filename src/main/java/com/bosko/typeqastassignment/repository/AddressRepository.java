package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
