package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Category;
import com.crudecommerce.desafio.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    Optional<Category> update(Long id, Category category);

    Optional<Category> delete(Long id);
}
