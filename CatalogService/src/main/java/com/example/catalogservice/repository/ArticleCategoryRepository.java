package com.example.catalogservice.repository;

import com.example.catalogservice.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory,Integer> {
}
