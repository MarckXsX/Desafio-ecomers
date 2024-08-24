package com.crudecommerce.desafio.repositories;

import com.crudecommerce.desafio.entities.Order;
import org.apache.juli.logging.Log;
import org.springframework.data.repository.CrudRepository;

public interface OrderReposity extends CrudRepository<Order, Long> {
}
