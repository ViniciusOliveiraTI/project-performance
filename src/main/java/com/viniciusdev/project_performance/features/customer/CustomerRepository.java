package com.viniciusdev.project_performance.features.customer;

import com.viniciusdev.project_performance.features.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> { }
