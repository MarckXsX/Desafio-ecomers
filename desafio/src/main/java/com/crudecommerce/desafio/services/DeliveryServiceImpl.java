package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Customer;
import com.crudecommerce.desafio.entities.Delivery;
import com.crudecommerce.desafio.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    @Autowired
    private DeliveryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Delivery> findAll() {
        return (List<Delivery>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Delivery> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Delivery save(Delivery delivery) {
        return repository.save(delivery);
    }

    @Override
    @Transactional()
    public Optional<Delivery> update(Long id, Delivery delivery) {
        Optional<Delivery> deliveryOptional = repository.findById(id);
        if(deliveryOptional.isPresent()){
            Delivery deliveryDB = deliveryOptional.orElseThrow();
            deliveryDB.setType(delivery.getType());
            deliveryDB.setStatus(delivery.getStatus());

            return Optional.of(repository.save(deliveryDB));
        }
        return deliveryOptional;
    }

    @Override
    @Transactional()
    public Optional<Delivery> delete(Long id) {
        Optional<Delivery> deliveryOptional = repository.findById(id);
        deliveryOptional.ifPresent(delivery -> {
            repository.delete(delivery);
        });
        return deliveryOptional;
    }
}
