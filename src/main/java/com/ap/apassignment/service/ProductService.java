package com.ap.apassignment.service;


import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.domain.entity.Product;
import com.ap.apassignment.repository.CategoryRepository;
import com.ap.apassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public List<ProductResponse> getProductListByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);

        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : products) {
            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId())
                    .brandName(product.getBrandName())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .categoryId(category.getId())
                    .categoryName(category.getCategoryName())
                    .build();

            productResponseList.add(productResponse);
        }

        return productResponseList;
    }


}
