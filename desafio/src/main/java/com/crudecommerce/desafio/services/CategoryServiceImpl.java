package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Category;
import com.crudecommerce.desafio.entities.Customer;
import com.crudecommerce.desafio.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) repository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Optional<Category> update(Long id, Category category) {
        Optional<Category> categoryOptional = repository.findById(id);
        if(categoryOptional.isPresent()){
            Category categoryDB = categoryOptional.orElseThrow();
            categoryDB.setCategory_name(category.getCategory_name());
            categoryDB.setCategory_type(category.getCategory_type());

            return Optional.of(repository.save(categoryDB));
        }
        return categoryOptional;
    }

    @Override
    public Optional<Category> delete(Long id) {
        Optional<Category> categoryOptional = repository.findById(id);
        categoryOptional.ifPresent(category -> {
            repository.delete(category);
        });
        return categoryOptional;
    }
}
