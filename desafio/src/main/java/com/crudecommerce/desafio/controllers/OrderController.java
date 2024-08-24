package com.crudecommerce.desafio.controllers;

import com.crudecommerce.desafio.entities.*;
import com.crudecommerce.desafio.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/API/Orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public List<Order> list(){ //No cambios
        return service.findAll();
    }

    @GetMapping("/{id}") //No cambios
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Order> optionalOrder = service.findById(id);
        if(optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) { //Funcional
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveOrder(order));
    }

    @PostMapping("/{orderId}/products") //Funcional
    public ResponseEntity<?> addProductsToOrder(@PathVariable Long orderId, @RequestBody List<Long> productIds) {
        return ResponseEntity.ok(service.adProduct(orderId, productIds));
    }

    @PutMapping("/{orderid}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderid, @RequestBody Order order){
        List<Long> produtsId = order.getProducts().stream().map(Product::getId).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(orderid,order,produtsId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Order> orderOptional = service.delete(id);
        if(orderOptional.isPresent()){
            return ResponseEntity.ok(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
