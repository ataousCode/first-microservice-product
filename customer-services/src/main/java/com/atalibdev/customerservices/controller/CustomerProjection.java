package com.atalibdev.customerservices.controller;

import com.atalibdev.customerservices.entities.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "customer_projection", types = Customer.class)
public interface CustomerProjection {
    public Long getCustomerId();
    public String getCustomerFirstName();
    public String getCustomerLastName();
    public String getCustomerEmail();
}
