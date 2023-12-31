package org.atalibdev.billingservice.model;

import lombok.Data;

@Data
public class Customer {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
}
