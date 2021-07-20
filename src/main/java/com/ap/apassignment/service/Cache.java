package com.ap.apassignment.service;


import com.ap.apassignment.domain.dto.CategoryResponse;
import com.ap.apassignment.domain.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Cache {

    @Autowired
    private CategoryService categoryService;

    private final String CACHE_KEY = "category";
    private final Map<String, List<CategoryResponse>> categoryCache = new HashMap<String, List<CategoryResponse>>();

    public List<CategoryResponse> findCategoryCache() {
        // 데이터가 적재되지 않았으면 데이터 저장소(DB)에서 데이터 가져오기
        if (categoryCache.isEmpty()) {
            synchronized (categoryCache) {
                if (categoryCache.isEmpty()) {

                    List<CategoryResponse> categoryList = categoryService.getCategoryList();

                    Map<String, List<CategoryResponse>> map = new HashMap<String, List<CategoryResponse>>();
                    map.put(CACHE_KEY, categoryList);

                    categoryCache.clear();
                    categoryCache.putAll(map);
                }
            }
        }

        return categoryCache.get(CACHE_KEY);
    }

}
