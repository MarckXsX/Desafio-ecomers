package com.crudecommerce.desafio.repositories;

import com.crudecommerce.desafio.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
