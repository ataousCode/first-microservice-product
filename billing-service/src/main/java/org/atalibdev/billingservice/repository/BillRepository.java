package org.atalibdev.billingservice.repository;

import org.atalibdev.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill, Long> {
}
