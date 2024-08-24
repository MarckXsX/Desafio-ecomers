package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    Optional<Customer> update(Long id, Customer customer);

    Optional<Customer> delete(Long id);
}
