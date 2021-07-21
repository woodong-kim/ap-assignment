package com.ap.apassignment.service;

import com.ap.apassignment.domain.dto.CategoryResponse;

import java.util.List;

public interface Cache {

    List<CategoryResponse> findCategoryCache();

}
