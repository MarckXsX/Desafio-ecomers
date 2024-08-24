package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Delivery;
import com.crudecommerce.desafio.entities.Order;
import com.crudecommerce.desafio.entities.Product;
import com.crudecommerce.desafio.repositories.OrderReposity;
import com.crudecommerce.desafio.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderReposity repository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return (List<Order>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    @Override
    @Transactional()
    public Order adProduct(Long id, List<Long> idsProducts) {
        Optional<Order> orderOptional = repository.findById(id);
        if (orderOptional.isPresent()){
            Order ordenDB = orderOptional.orElseThrow();

            Iterable<Product> products = productRepository.findAllById(idsProducts);

            ordenDB.getProducts().addAll((List<Product>)products);

            return repository.save(ordenDB);
        }

        return null;
    }

    @Override
    @Transactional()
    public Optional<Order> update(Long id, Order orderUpdate, List<Long> idsProducts) {
        Optional<Order> optionalOrder = repository.findById(id);
        if(optionalOrder.isPresent()){
            Order ordenDB = optionalOrder.orElseThrow();
            ordenDB.setCustomer(orderUpdate.getCustomer());
            ordenDB.setDelivery(orderUpdate.getDelivery());
            ordenDB.setOrder_date(orderUpdate.getOrder_date());

            if (idsProducts != null && !idsProducts.isEmpty()) {
                Iterable<Product> products = productRepository.findAllById(idsProducts);
                ordenDB.setProducts((List<Product>) products);
            }

            return Optional.of( repository.save(ordenDB));
        }

        return optionalOrder;
    }

    @Override
    public Optional<Order> delete(Long id) {
        Optional<Order> orderOptional = repository.findById(id);
        orderOptional.ifPresent(order -> {
            repository.delete(order);
        });
        return orderOptional;
    }
}
