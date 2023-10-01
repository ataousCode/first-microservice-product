package com.atalibdev;

import com.atalibdev.entities.Product;
import com.atalibdev.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ProductRepository productRepository,
						  RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Product.class);
			Product product1 = Product.builder()
					.name("iPhone")
					.price(2511.12)
					.quantity(5)
					.build();
			Product product2 = Product.builder()
					.name("DEL")
					.price(1458.57)
					.quantity(8)
					.build();
			Product product3 = Product.builder()
					.name("Muse")
					.price(55.12)
					.quantity(3)
					.build();

			productRepository.saveAll(List.of(
					product1, product2, product3
			));
		};
	}
}
