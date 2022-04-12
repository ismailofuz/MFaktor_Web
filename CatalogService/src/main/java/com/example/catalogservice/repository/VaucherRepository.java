package com.example.catalogservice.repository;

import com.example.catalogservice.entity.EventType;
import com.example.catalogservice.entity.Vaucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaucherRepository extends JpaRepository<Vaucher, Integer> {
}
