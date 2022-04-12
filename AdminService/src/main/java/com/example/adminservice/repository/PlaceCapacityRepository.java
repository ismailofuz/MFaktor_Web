package com.example.adminservice.repository;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.PlaceCapacity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCapacityRepository extends JpaRepository<PlaceCapacity, Integer> {
}
