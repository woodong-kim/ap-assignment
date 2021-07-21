package com.ap.apassignment.service;


import com.ap.apassignment.domain.dto.CategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class CacheImpl implements Cache{

    @Autowired
    private CategoryService categoryService;

    private final String CACHE_KEY = "category";
    private final Map<String, List<CategoryResponse>> categoryCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadCategoryCache() {
        findCategoryCache();
    }

    @Override
    public List<CategoryResponse> findCategoryCache() {

        // Cache miss, DB에서 데이터 가져오기
        if (categoryCache.isEmpty()) {
            //synchronized (categoryCache) {
                List<CategoryResponse> categoryList = categoryService.getCategoryList();

                Map<String, List<CategoryResponse>> map = new ConcurrentHashMap<String, List<CategoryResponse>>();
                map.put(CACHE_KEY, categoryList);

                categoryCache.clear();
                categoryCache.putAll(map);
            //}
        }

        return categoryCache.get(CACHE_KEY);
    }

}
