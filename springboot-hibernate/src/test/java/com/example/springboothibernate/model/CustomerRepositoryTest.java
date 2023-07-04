package com.example.springboothibernate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // 테스트 직후 롤백됨 ; Transactional 어노테이션 때문
@Import(CustomerRepository.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        Customer customer = Customer.builder()
                .name("코스")
                .tel("0103333")
                .build();


        // When
        customerRepository.save(customer); // 비영속객체 -> 영속화(persist) -> 영속객체 -> PK가 없네? -> flush (Insert)
        // 영속화 되는 순간 heap영역의 데이터와 테이블의 로우가 동기화됨 (데이터의 일치)


        entityManager.clear(); // 캐시 초기화
    }

    @Test
    public void findById_test() {
        // Given
        Long id = 1L;

        // When
        Customer customerPS = customerRepository.findById(id); // 영속 객체

        // 디폴트 생성자가 호출되는데 변수들이 초기화 되는 이유 ; 리플렉션을 통해 private에 접근해서 값 초기회
        System.out.println(customerPS.getName());


        // Then
    }

    @Test
    public void save_test() {
        // Given
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0102222")
                .build();


        // Then
        customerRepository.save(customer);
    }

    @Test
    public void findAll_test() {
        // Given
        Customer customer = Customer.builder()
                .name("킴킴킴")
                .tel("0103232323")
                .build();
        customerRepository.save(customer);

        // When
        List<Customer> customerList = customerRepository.findAll();

        // Then
        assertThat(customerList.size()).isEqualTo(2);
    }

}