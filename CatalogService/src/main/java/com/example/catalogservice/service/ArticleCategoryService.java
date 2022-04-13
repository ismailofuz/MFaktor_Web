package com.example.catalogservice.service;

import com.example.catalogservice.entity.ArticleCategory;
import com.example.catalogservice.payload.ApiResponse;
import com.example.catalogservice.payload.ArticleCategoryDto;
import com.example.catalogservice.repository.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryService {
    @Autowired
    ArticleCategoryRepository articleCategoryRepository;


    public ApiResponse add(ArticleCategoryDto dto) {
        ArticleCategory articleCategory=new ArticleCategory();
        articleCategory.setName(dto.getName());
        articleCategory.setActive(dto.isActive());
        ArticleCategory saveArticleCategory = articleCategoryRepository.save(articleCategory);
        return new ApiResponse("Saqlandi",true,saveArticleCategory);
    }
}
