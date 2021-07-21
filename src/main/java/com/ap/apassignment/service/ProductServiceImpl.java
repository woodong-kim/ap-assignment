package com.ap.apassignment.service;


import com.ap.apassignment.domain.dto.ProductRequest;
import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.domain.entity.Product;
import com.ap.apassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
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

    @Override
    public ProductResponse getProductInfo(Long id) {
        ProductResponse productResponse = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productResponse = response(product);
        }

        return productResponse;
    }

    @Override
    public ProductResponse updateProductInfo(ProductRequest productRequest) {
        ProductResponse productResponse = null;
        Optional<Product> optionalProduct = productRepository.findById(productRequest.getId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (StringUtils.hasText(productRequest.getProductName())) {
                product.setProductName(productRequest.getProductName());
            }
            if (productRequest.getProductPrice() != null) {
                product.setProductPrice(productRequest.getProductPrice());
            }
            productResponse = response(productRepository.save(product));

        }

        return productResponse;
    }

    private ProductResponse response(Product product) {

        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .brandName(product.getBrandName())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getCategoryName())
                .build();
        return productResponse;

    }


}
