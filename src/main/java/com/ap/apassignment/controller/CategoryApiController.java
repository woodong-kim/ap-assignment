package com.ap.apassignment.controller;


import com.ap.apassignment.domain.dto.CategoryRequest;
import com.ap.apassignment.domain.dto.CategoryResponse;
import com.ap.apassignment.domain.dto.ProductResponse;
import com.ap.apassignment.domain.entity.Category;
import com.ap.apassignment.service.Cache;
import com.ap.apassignment.service.CategoryService;
import com.ap.apassignment.service.ProductService;
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


/**
 * 카테고리 정보 관련 API Controller
 * @author woodong.kim
 * @version 0.1
 */

@Slf4j
@RestController
@RequestMapping("/categorys")
@RequiredArgsConstructor
public class CategoryApiController {

    private final Cache cache;
    private final CategoryService categoryService;
    private final ProductService productService;


    /**
     * 카테고리 목록을 조회
     * 캐시된 카테고리 데이터를 전달 하는 것에 주의.
     *
     * @param
     * @return List 카테고리 목록(카테고리ID, 카테고리명)
     */
    @GetMapping("")
    public List<CategoryResponse> getCategoryList(){
        log.info("########## CategoryApiController getCategoryList()");
        //return categoryService.getCategoryList();
        return cache.findCategoryCache();
    }

    /**
     * 카테고리 정보 조회
     * 특정 카테고리의 정보를 조회
     *
     * @param id 카테고리ID
     * @return 카테고리 속성 정보(카테고리ID, 카테고리명)
     */
    @GetMapping("/{id}")
    public CategoryResponse getCategoryInfo(@PathVariable Integer id) {
        return categoryService.getCategoryInfo(id);
    }

    /**
     * 카테고리별 상품목록 조회
     * 특정 카테고리에 속한 상품의 목록 정보를 조회
     *
     * @param id 카테고리ID
     * @return List 상품정보(상품ID, 브랜드, 상품명, 상품가격, 카테고리ID, 카테고리명) 목록
     */
    @GetMapping("/{id}/products")
    public List<ProductResponse> getProductListByCategory(@PathVariable Integer id){

        List<ProductResponse> productList = new ArrayList<>();
        Optional<Category> optionalCategory = categoryService.getCategory(id);
        if (optionalCategory.isPresent()) {
            productList = productService.getProductListByCategory(optionalCategory.get());
        }

        return productList;
    }

    /**
     * 신규 카테고리 생성
     * 신규 카테고리 데이터를 생성 한다
     *
     * @param categoryRequest 생성정보(카테고리ID, 카테고리명, 부모카테고리ID, 카테고리DEPTH)
     * @return ResponseEntity(처리결과 응답)
     */
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

    /**
     * 카테고리 정보 수정
     * 특정 카테고리의 카테고리명을 수정한다.
     *
     * @param categoryRequest 변경정보(카테고리ID, 카테고리명)
     * @return ResponseEntity(처리결과 응답)
     */
    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryRequest categoryRequest, BindingResult bindingResult) {

        log.info("########  updateCategory : " + categoryRequest.toString());
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        CategoryResponse categoryResponse = categoryService.updateCategory(categoryRequest);

        return ResponseEntity.ok(categoryResponse);
    }

    /**
     * 특정 카테고리 삭제
     * 특정 카테고리를 삭제한다.
     *
     * @param id 카테고리ID
     * @return ResponseEntity(처리결과 응답)
     */
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
