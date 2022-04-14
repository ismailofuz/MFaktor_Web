package com.example.clientservice.repository;

import com.example.clientservice.entity.AdsSource;
import com.example.clientservice.entity.PaymentColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsSourceRepository extends JpaRepository<AdsSource, Integer> {

}
