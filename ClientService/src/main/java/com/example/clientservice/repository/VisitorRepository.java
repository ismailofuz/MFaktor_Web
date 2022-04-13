package com.example.clientservice.repository;

import com.example.clientservice.entity.Payment;
import com.example.clientservice.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
}