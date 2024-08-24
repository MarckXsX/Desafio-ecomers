package com.crudecommerce.desafio.controllers;

import com.crudecommerce.desafio.entities.Customer;
import com.crudecommerce.desafio.services.CustomerService;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API/Customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> list(){ //No cambios
        return service.findAll();
    }

    @GetMapping("/{id}") //No cambios
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Customer> optionalCustomer = service.findById(id);
        if(optionalCustomer.isPresent()){
            return ResponseEntity.ok(optionalCustomer.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //No cambios
    public ResponseEntity<?> create(@RequestBody Customer customer){ //No cambios
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer){

        Optional<Customer> customerOptional = service.update(id,customer);
        if(customerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(customerOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Customer> customerOptional = service.delete(id);
        if(customerOptional.isPresent()){
            return ResponseEntity.ok(customerOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
