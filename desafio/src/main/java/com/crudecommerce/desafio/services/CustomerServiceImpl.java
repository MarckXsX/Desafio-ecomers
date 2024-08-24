package com.crudecommerce.desafio.services;

import com.crudecommerce.desafio.entities.Customer;
import com.crudecommerce.desafio.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    @Transactional()
    public Optional<Customer> update(Long id, Customer customer) {
        Optional<Customer> customerOptional = repository.findById(id);
        if(customerOptional.isPresent()){
            Customer customerDB = customerOptional.orElseThrow();
            customerDB.setName(customer.getName());
            customerDB.setEmail(customer.getEmail());
            customerDB.setAdress(customer.getAdress());

            return Optional.of(repository.save(customerDB));
        }

        return customerOptional;
    }

    @Override
    @Transactional()
    public Optional<Customer> delete(Long id) {
        Optional<Customer> customerOptional = repository.findById(id);
        customerOptional.ifPresent(customer -> {
            repository.delete(customer);
        });
        return customerOptional;
    }
}
