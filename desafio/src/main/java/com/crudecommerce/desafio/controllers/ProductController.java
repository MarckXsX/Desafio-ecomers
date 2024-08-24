package com.crudecommerce.desafio.controllers;

import com.crudecommerce.desafio.entities.Delivery;
import com.crudecommerce.desafio.entities.Product;
import com.crudecommerce.desafio.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API/Products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> list(){ //No cambios
        return service.findAll();
    }

    @GetMapping("/{id}") //No cambios
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Product> optionalProduct = service.findById(id);
        if(optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //No cambios
    public ResponseEntity<?> create(@RequestBody Product product){ //No cambios
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product){

        Optional<Product> optionalProduct = service.update(id,product);
        if(optionalProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> optionalProduct = service.delete(id);
        if(optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
