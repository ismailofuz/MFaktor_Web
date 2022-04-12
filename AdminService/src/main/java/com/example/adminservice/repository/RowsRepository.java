package com.example.adminservice.repository;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Rows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RowsRepository extends JpaRepository<Rows, Integer> {
}
