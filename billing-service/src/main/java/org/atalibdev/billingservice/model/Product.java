package org.atalibdev.billingservice.model;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String name;
    private double price;
    private int quantity;
}
