package com.ap.apassignment.service;


import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.domain.entity.Product;
import com.ap.apassignment.repository.CategoryRepository;
import com.ap.apassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public List<Product> getProductListByCategory(Category category) {
        return productRepository.findByCategory(category);
    }


}
