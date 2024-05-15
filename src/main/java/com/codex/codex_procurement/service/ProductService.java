package com.codex.codex_procurement.service;

import com.codex.codex_procurement.dto.request.NewProductRequest;
import com.codex.codex_procurement.dto.request.SearchProductRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Product;
import org.springframework.data.domain.Page;


public interface ProductService {
    ProductResponse create(NewProductRequest newProductRequest);
    ProductResponse getById(String id);
    Page<ProductResponse> getAll(SearchProductRequest request);
    Product update(Product product);
    void deleteById(String id);

}
