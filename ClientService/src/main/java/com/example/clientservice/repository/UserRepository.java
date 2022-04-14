package com.example.clientservice.repository;

import com.example.clientservice.entity.Payment;
import com.example.clientservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
 boolean existsByPhoneNumber(String phoneNumber);
}
