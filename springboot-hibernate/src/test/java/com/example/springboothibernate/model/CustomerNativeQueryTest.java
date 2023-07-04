package com.example.springboothibernate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(CustomerNativeQuery.class)
@DataJpaTest
class CustomerNativeQueryTest {

    @Autowired
    private CustomerNativeQuery customerNativeQuery;

    @BeforeEach
    public void setUp() {
        Customer customer = Customer.builder()
                .name("코스")
                .tel("0103333")
                .build();
        customerNativeQuery.save(customer);

    }

    @Test
    public void save_test() {
        // Given
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("01022222222")
                .build();

        // When
        customerNativeQuery.save(customer);

        // Then
        Customer customerPS = customerNativeQuery.findById(2L);
        assertThat(customerPS.getName()).isEqualTo("코스");

    }

    @Test
    public void findById_test() {
        // Given
        Long id = 1L;

        // When
        Customer findCustomer = customerNativeQuery.findById(id);

        // Then
        assertThat(findCustomer.getId()).isEqualTo(1);
    }

}