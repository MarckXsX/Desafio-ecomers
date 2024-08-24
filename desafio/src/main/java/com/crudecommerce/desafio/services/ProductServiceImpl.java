package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Delivery;
import com.crudecommerce.desafio.entities.Product;
import com.crudecommerce.desafio.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional()
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isPresent()){
            Product productDB= productOptional.orElseThrow();
            productDB.setProduct_name(product.getProduct_name());
            productDB.setCategory(product.getCategory());

            return Optional.of(repository.save(productDB));
        }
        return productOptional;
    }

    @Override
    @Transactional()
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(product -> {
            repository.delete(product);
        });
        return productOptional;
    }
}
