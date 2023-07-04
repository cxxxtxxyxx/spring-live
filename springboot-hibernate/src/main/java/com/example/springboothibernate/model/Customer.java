package com.example.springboothibernate.model;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tel;

    public Customer() {
        System.out.println("Customer 디폴트 생성자 호출됨");
    }

    public Customer(Long id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        System.out.println(id + name + tel);
        System.out.println("풀 Customer 생성자 호출됨");
    }
}
