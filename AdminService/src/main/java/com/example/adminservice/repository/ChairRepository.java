package com.example.adminservice.repository;

import com.example.adminservice.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChairRepository extends JpaRepository<Chair, Integer> {
}
