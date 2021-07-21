package com.ap.apassignment.controller;


import com.ap.apassignment.domain.dto.CategoryRequest;
import com.ap.apassignment.domain.dto.CategoryResponse;
import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("")
    public ResponseEntity createCategory(@Valid @RequestBody CategoryRequest categoryRequest, BindingResult bindingResult) {

        log.info("########  createCategory : " + categoryRequest.toString());

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
        if (categoryResponse == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category is Exist");
        }
        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryRequest categoryRequest, BindingResult bindingResult) {

        log.info("########  updateCategory : " + categoryRequest.toString());
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        CategoryResponse categoryResponse = categoryService.updateCategory(categoryRequest);

        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {

        log.info("########  deleteCategory ");
        boolean deleteCategory = categoryService.deleteCategory(id);
        if (! deleteCategory) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Category delete Fail");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Delete OK");
    }

}
