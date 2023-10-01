package org.atalibdev.billingservice.service;

import org.atalibdev.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
    @GetMapping(path = "/products/{productId}")
    Product findProductById(@PathVariable("productId") Long productId);
    @GetMapping(path = "/products")
    PagedModel<Product> fetchProducts();
}
