package org.atalibdev.billingservice;

import org.atalibdev.billingservice.entities.Bill;
import org.atalibdev.billingservice.entities.ProductItem;
import org.atalibdev.billingservice.model.Customer;
import org.atalibdev.billingservice.model.Product;
import org.atalibdev.billingservice.repository.BillRepository;
import org.atalibdev.billingservice.repository.ProductItemRepository;
import org.atalibdev.billingservice.service.CustomerRestClient;
import org.atalibdev.billingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(BillRepository billRepository,
						  ProductItemRepository itemRepository,
						  CustomerRestClient customerRestClient,
						  ProductRestClient productRestClient
						  ) {
		return args -> {
			Collection<Product> products =
					productRestClient.fetchProducts().getContent();

			Long customerId = 1L;
			Customer customer = customerRestClient.findCustomerById(customerId);
			if (customer == null) throw new RuntimeException("Customer not found.");

			Bill bill = new Bill();
			bill.setBillDate(new Date());
			bill.setCustomerId(customerId);
			Bill savedBill = billRepository.save(bill);
			products.forEach(product -> {
				ProductItem productItem = new ProductItem();
				productItem.setBill(savedBill);
				productItem.setQuantity(1 + new Random().nextInt(10));
				productItem.setProductId(product.getProductId());
				productItem.setPrice(product.getPrice());
				productItem.setDiscount(Math.random());
				itemRepository.save(productItem);
			});

		};
	}
}
