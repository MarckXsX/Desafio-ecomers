package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Delivery;
import com.crudecommerce.desafio.entities.Order;
import com.crudecommerce.desafio.entities.Product;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order saveOrder(Order order);

    Order adProduct(Long id, List<Long> idsProducts);

    Optional<Order> update(Long id, Order order, List<Long> idsProducts);

    Optional<Order> delete(Long id);
}
