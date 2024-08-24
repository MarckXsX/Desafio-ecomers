package com.crudecommerce.desafio.controllers;

import com.crudecommerce.desafio.entities.Category;
import com.crudecommerce.desafio.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API/Categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> list(){ //No cambios
        return service.findAll();
    }

    @GetMapping("/{id}") //No cambios
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Category> optionalCategory = service.findById(id);
        if(optionalCategory.isPresent()){
            return ResponseEntity.ok(optionalCategory.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //No cambios
    public ResponseEntity<?> create(@RequestBody Category category){ //No cambios
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category){

        Optional<Category> categoryOptional = service.update(id,category);
        if(categoryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Category> categoryOptional = service.delete(id);
        if(categoryOptional.isPresent()){
            return ResponseEntity.ok(categoryOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
