package com.example.springboothibernate.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomerRepository {

    private final EntityManager entityManager;

    @Transactional
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Transactional
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        entityManager.remove(customer);
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return entityManager.createQuery("select ct from Customer ct", Customer.class)
                .getResultList();
    }
}
