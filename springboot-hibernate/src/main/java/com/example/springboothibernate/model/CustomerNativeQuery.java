package com.example.springboothibernate.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerNativeQuery {

    private final EntityManager entityManager;

    @Transactional
    public void save(Customer customer) {
        Query query = entityManager.createNativeQuery("INSERT INTO customer (name, tel) VALUES (:name, :tel)");
        query.setParameter("name", customer.getName());
        query.setParameter("tel", customer.getTel());
        query.executeUpdate();
    }

    @Transactional
    public void update(Customer customer) {
        Query query = entityManager.createNativeQuery("UPDATE customer SET name = :name, tel = :tel WHERE id = :id");
        query.setParameter("name", customer.getName());
        query.setParameter("tel", customer.getTel());
        query.setParameter("id", customer.getId());
        query.executeUpdate();
    }

    @Transactional
    public void delete(Customer customer) {
        Query query = entityManager.createNativeQuery("DELETE FROM customer WHERE id = :id");
        query.setParameter("id", customer.getId());
        query.executeUpdate();
    }

    public Customer findById(Long id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM customer WHERE id = :id", Customer.class);
        query.setParameter("id", id);
        return (Customer) query.getSingleResult();
    }

    public List<Customer> findAll(int page) {
        Query query = entityManager.createNativeQuery("select * from customer", Customer.class);
        return query.getResultList();
    }
}
