package org.atalibdev.billingservice.controller;

import lombok.RequiredArgsConstructor;
import org.atalibdev.billingservice.entities.Bill;
import org.atalibdev.billingservice.repository.BillRepository;
import org.atalibdev.billingservice.repository.ProductItemRepository;
import org.atalibdev.billingservice.service.CustomerRestClient;
import org.atalibdev.billingservice.service.ProductRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillRestController {

    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    @GetMapping(path = "/fullBill/{id}")
    public Bill bill(@PathVariable("id") Long id){
        Bill bill = billRepository.findById(id)
                        .orElseGet(null);
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(product -> {
            product.setProduct(productRestClient.findProductById(
                    product.getProductId()
            ));
        });
        return bill;
    }
}
