package com.bosko.typeqastassignment.repository;

import com.bosko.typeqastassignment.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Client repository for handling basic operation with database.
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    /**
     * Method for getting list of clients from database with below parameter
     * @param lastName clients last name which is needed for querying the database
     * @return list of clients
     */
    List<Client> findByLastName(String lastName);

    /**
     * Method for getting the client from database
     * @param firstName clients first name which is needed for querying the database
     * @param lastName clients last name which is needed for querying the database
     * @return client
     */
    Optional<Client> findByFirstNameAndLastName(String firstName, String lastName);
}
