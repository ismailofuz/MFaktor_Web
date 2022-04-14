package com.example.clientservice.repository;

import com.example.clientservice.entity.Payment;
import com.example.clientservice.entity.PaymentColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentColorRepository extends JpaRepository<PaymentColor, Integer> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
    boolean existsByNameAndIdNot(String name, Integer id);
    boolean existsByCodeAndIdNot(String code, Integer id);
}
