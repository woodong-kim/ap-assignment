package com.ap.apassignment.controller;


import com.ap.apassignment.domain.dto.ProductRequest;
import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.service.CategoryService;
import com.ap.apassignment.service.CategoryServiceImpl;
import com.ap.apassignment.service.ProductService;
import com.ap.apassignment.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductResponse getProductInfo(@PathVariable Long id) {
        return productService.getProductInfo(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProductInfo(@Valid ProductRequest productRequest, BindingResult bindingResult) {

        log.info("########  updateProductInfo : " + productRequest.toString());
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        ProductResponse productResponse = productService.updateProductInfo(productRequest);

        return ResponseEntity.ok(productResponse);
    }


}
