package com.crudecommerce.desafio.repositories;

import com.crudecommerce.desafio.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
