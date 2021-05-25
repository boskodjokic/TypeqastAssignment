package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByLastName(String lastName);

    Optional<Client> findByFirstNameAndLastName(String firstName, String lastName);
}
