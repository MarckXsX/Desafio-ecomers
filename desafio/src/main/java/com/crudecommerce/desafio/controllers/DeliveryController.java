package com.crudecommerce.desafio.controllers;

import com.crudecommerce.desafio.entities.Delivery;
import com.crudecommerce.desafio.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API/Deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService service;

    @GetMapping
    public List<Delivery> list(){ //No cambios
        return service.findAll();
    }

    @GetMapping("/{id}") //No cambios
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Delivery> optionalDelivery = service.findById(id);
        if(optionalDelivery.isPresent()){
            return ResponseEntity.ok(optionalDelivery.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //No cambios
    public ResponseEntity<?> create(@RequestBody Delivery delivery){ //No cambios
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(delivery));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Delivery delivery){

        Optional<Delivery> deliveryOptional = service.update(id,delivery);
        if(deliveryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(deliveryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Delivery> deliveryOptional = service.delete(id);
        if(deliveryOptional.isPresent()){
            return ResponseEntity.ok(deliveryOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
