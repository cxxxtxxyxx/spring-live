package com.example.springboothibernate.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
