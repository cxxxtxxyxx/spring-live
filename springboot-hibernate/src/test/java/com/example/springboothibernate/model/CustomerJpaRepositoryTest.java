package com.example.springboothibernate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerJpaRepositoryTest {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        Customer customer = Customer.builder()
                .name("강강강")
                .tel("123123123")
                .build();

        customerJpaRepository.save(customer);
        entityManager.clear();
    }

    @Test
    public void findById_test() {
        // Given
        Long id = 1L;

        // When
        customerJpaRepository.findById(id);

        // Then
    }
}