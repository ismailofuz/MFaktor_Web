package com.example.catalogservice.service;

import com.example.catalogservice.entity.ArticleCategory;
import com.example.catalogservice.payload.AdsSourceDto;
import com.example.catalogservice.payload.ApiResponse;
import com.example.catalogservice.payload.ArticleCategoryDto;
import com.example.catalogservice.repository.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ApiResponse getAll() {
        return new ApiResponse("Found",true, articleCategoryRepository.findAll());
    }

    public ApiResponse getById(Integer id) {
        Optional<ArticleCategory> optionalArticleCategory = articleCategoryRepository.findById(id);
        if (optionalArticleCategory.isPresent())
        {
            return new ApiResponse("Found",true,optionalArticleCategory);
        }
        return new ApiResponse("Not Found", false);
    }

    public ApiResponse edit(Integer id, AdsSourceDto dto) {
        Optional<ArticleCategory> byId = articleCategoryRepository.findById(id);
        if (byId.isPresent()) {
            ArticleCategory articleCategory = byId.get();
            articleCategory.setName(dto.getName());
            articleCategory.setActive(dto.isActive());
            ArticleCategory save = articleCategoryRepository.save(articleCategory);
            return new ApiResponse("Edited", true, save);
        }
        return new ApiResponse("Article Category not found", false);

    }

    public ApiResponse delete(Integer id) {
        Optional<ArticleCategory> byId = articleCategoryRepository.findById(id);
        if (byId.isPresent()) {
            ArticleCategory articleCategory = byId.get();
            articleCategory.setActive(false);
            ArticleCategory save = articleCategoryRepository.save(articleCategory);
            return new ApiResponse("active is false", true,save);
        }
        return new ApiResponse("Article Category not found", false);
    }
}
