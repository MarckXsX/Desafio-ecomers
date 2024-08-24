package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {

    List<Delivery> findAll();

    Optional<Delivery> findById(Long id);

    Delivery save(Delivery delivery);

    Optional<Delivery> update(Long id, Delivery delivery);

    Optional<Delivery> delete(Long id);
}
