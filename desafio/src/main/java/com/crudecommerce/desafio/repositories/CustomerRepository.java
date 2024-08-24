package com.crudecommerce.desafio.repositories;

import com.crudecommerce.desafio.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
