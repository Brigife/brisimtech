package com.tech.brisim.cusmangt.profile.service.implem;

import com.tech.brisim.cusmangt.profile.entity.Customer;
import com.tech.brisim.cusmangt.profile.repository.CustomerRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor for dependency injection
    public CustomerService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Register a new customer
    public Customer registerCustomer(Customer customer) {
        // Check if the email is already in use
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new ValidationException("Email already in use");
        }

        // Hash the customer's password before saving
        customer.setPassword(hashPassword(customer.getPassword()));

        // Set the creation and update timestamps
        customer.setCreatedAt(new Date());
        customer.setUpdatedAt(new Date());

        return customerRepository.save(customer);
    }

    // Fetch a customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // Update a customer's information
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update customer fields
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setUpdatedAt(new Date()); // Set the updated timestamp

        // Hash password if it has been changed
        if (!passwordEncoder.matches(updatedCustomer.getPassword(), existingCustomer.getPassword())) {
            existingCustomer.setPassword(hashPassword(updatedCustomer.getPassword()));
        }

        return customerRepository.save(existingCustomer);
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Fetch a customer by email
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Helper method to hash passwords
    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
