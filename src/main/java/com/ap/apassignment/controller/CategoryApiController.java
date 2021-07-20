package com.ap.apassignment.controller;


import com.ap.apassignment.domain.dto.CategoryResponse;
import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.service.Cache;
import com.ap.apassignment.service.CategoryService;
import com.ap.apassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/categorys")
@RequiredArgsConstructor
public class CategoryApiController {

    private final Cache cache;
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("")
    public List<CategoryResponse> getCategoryList(){
        log.info("########## CategoryApiController getCategoryList()");
        //return categoryService.getCategoryList();
        return cache.findCategoryCache();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryInfo(@PathVariable Integer id) {
        return categoryService.getCategoryInfo(id);
    }

    @GetMapping("/{id}/products")
    public List<ProductResponse> getProductListByCategory(@PathVariable Integer id){

        List<ProductResponse> productList = new ArrayList<>();
        Optional<Category> optionalCategory = categoryService.getCategory(id);
        if (optionalCategory.isPresent()) {
            productList = productService.getProductListByCategory(optionalCategory.get());
        }

        return productList;
    }

}
