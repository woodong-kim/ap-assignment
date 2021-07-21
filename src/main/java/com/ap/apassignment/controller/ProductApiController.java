package com.ap.apassignment.controller;


import com.ap.apassignment.domain.dto.ProductRequest;
import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.service.CategoryService;
import com.ap.apassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 상품정보 정보 관련 API Controller
 * @author woodong.kim
 * @version 0.1
 */

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * 상품정보 조회
     * 특정 상품의 정보를 조회한다
     *
     * @param id 상품ID
     * @return Product 상품정보(상품ID, 브랜드, 상품명, 상품가격, 카테고리ID, 카테고리명)
     */
    @GetMapping("/{id}")
    public ProductResponse getProductInfo(@PathVariable Long id) {
        return productService.getProductInfo(id);
    }

    /**
     * 상품정보 수정
     * 특정 상품의 상품명, 가격 정보를 수정한다.
     *
     * @param productRequest 상품정보(상품ID, 상품명, 상품가격)
     * @return ResponseEntity(처리결과 응답)
     */
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
