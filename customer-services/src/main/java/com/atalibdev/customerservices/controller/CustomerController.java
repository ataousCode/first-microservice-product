package com.atalibdev.customerservices.controller;

import com.atalibdev.customerservices.entities.Customer;
import com.atalibdev.customerservices.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> fetch() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }
}
