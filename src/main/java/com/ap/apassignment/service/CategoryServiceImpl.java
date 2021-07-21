package com.ap.apassignment.service;


import com.ap.apassignment.domain.dto.CategoryRequest;
import com.ap.apassignment.domain.dto.CategoryResponse;
import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getCategoryList() {

        List<Category> categories = categoryRepository.findAll();
//        List<CategoryResponse> categoryResList = categories.stream()
//                .map(category -> response(category))
//                .collect(Collectors.toList());

        List<CategoryResponse> categoryResList = categories.stream()
                .map(category -> {
                    String categoryName = category.getCategoryName();
                    if (category.getDepth() > 1) {
                        for (Category parentCategory : categories) {
                            if (category.getParentNo() == parentCategory.getId()) {
                                categoryName = parentCategory.getCategoryName() + "-" + categoryName;
                            }
                        }
                    }
                    return CategoryResponse.builder()
                            .id(category.getId())
                            .categoryName(categoryName)
                            .build();
                })
                .collect(Collectors.toList());


        return categoryResList;
    }

    @Override
    public CategoryResponse getCategoryInfo(Integer id) {
        Optional<Category> categoryOptional = getCategory(id);
        return response(categoryOptional.orElseGet(Category::new));
    }

    @Override
    public Optional<Category> getCategory(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {

        CategoryResponse categoryResponse = null;
        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequest.getId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(categoryRequest.getCategoryName());
            return response(categoryRepository.save(category));
        }

        return categoryResponse;
    }


    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequest.getId());
        if (optionalCategory.isPresent()) return null;

        Category category = new Category();
        category.setId(categoryRequest.getId());
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setParentNo(categoryRequest.getParentNo());
        category.setDepth(categoryRequest.getDepth());

        return response(categoryRepository.save(category));

    }


    private CategoryResponse response(Category category) {

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();

        return categoryResponse;
    }


}
