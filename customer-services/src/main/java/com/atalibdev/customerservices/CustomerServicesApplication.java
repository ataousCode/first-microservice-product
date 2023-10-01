package com.atalibdev.customerservices;

import com.atalibdev.customerservices.entities.Customer;
import com.atalibdev.customerservices.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CustomerRepository customerRepository,
                          RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            Customer customer = Customer.builder()
                    .firstName("Aly")
                    .lastName("Ali")
                    .email("ali@gmail.com")
                    .build();
            Customer customer1 = Customer.builder()
                    .firstName("Aly 1")
                    .lastName("Ali 2")
                    .email("ali@gmail12.com")
                    .build();

            customerRepository.saveAll(List.of(customer, customer1));
        };
    }
}
