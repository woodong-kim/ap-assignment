package com.ap.apassignment.controller;


import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.domain.entity.Product;
import com.ap.apassignment.service.CategoryService;
import com.ap.apassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public List<Product> getProductList() {
        return productService.getProductList();
    }

}
