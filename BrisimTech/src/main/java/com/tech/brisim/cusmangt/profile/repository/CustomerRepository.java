package com.tech.brisim.cusmangt.profile.repository;

import com.tech.brisim.cusmangt.profile.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email); // Method to find a customer by email

    // You can add more custom query methods here if needed
}
