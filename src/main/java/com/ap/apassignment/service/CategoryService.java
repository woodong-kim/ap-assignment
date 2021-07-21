package com.ap.apassignment.service;

import com.ap.apassignment.domain.dto.CategoryRequest;
import com.ap.apassignment.domain.dto.CategoryResponse;
import com.ap.apassignment.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryResponse> getCategoryList();

    CategoryResponse getCategoryInfo(Integer id);

    Optional<Category> getCategory(Integer id);

    CategoryResponse updateCategory(CategoryRequest categoryRequest);

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    boolean deleteCategory(Integer id);
}
