package com.ap.apassignment.controller;


import com.ap.apassignment.domain.dto.CategoryResponse;
import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categorys")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping("")
    public List<CategoryResponse> getCategoryList(){

        log.info("########## CategoryApiController getCategoryList()");
        return categoryService.getCategoryList();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryInfo(@PathVariable Integer id) {
        return categoryService.getCategory(id);
    }



}
