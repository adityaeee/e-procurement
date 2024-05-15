package com.codex.codex_procurement.service;

import com.codex.codex_procurement.dto.request.NewCategoryRequest;
import com.codex.codex_procurement.dto.request.NewProductRequest;
import com.codex.codex_procurement.dto.request.SearchCategoryRequest;
import com.codex.codex_procurement.dto.request.SearchProductRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.entity.Product;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Category create(NewCategoryRequest newCategoryRequest);
    Category getById(String id);
    Page<Category> getAll(SearchCategoryRequest request);
//    void deleteById(String id);
}
