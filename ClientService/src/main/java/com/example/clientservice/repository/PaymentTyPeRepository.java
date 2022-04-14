package com.example.clientservice.repository;

import com.example.clientservice.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTyPeRepository extends JpaRepository<PaymentType, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
