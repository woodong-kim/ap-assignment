package com.ap.apassignment.service;

import com.ap.apassignment.domain.dto.ProductRequest;
import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.domain.entity.Category;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProductListByCategory(Category category);

    ProductResponse getProductInfo(Long id);

    ProductResponse updateProductInfo(ProductRequest productRequest);
}
