package com.example.adminservice.repository;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
}
