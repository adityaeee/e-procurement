package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.NewProductRequest;
import com.codex.codex_procurement.dto.request.SearchProductRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.entity.Product;
import com.codex.codex_procurement.repository.CategoryRepository;
import com.codex.codex_procurement.repository.ProductRepository;
import com.codex.codex_procurement.service.ProductService;
import com.codex.codex_procurement.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private  final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    @Override
    public ProductResponse create(NewProductRequest newProductRequest) {
        validationUtil.validate(newProductRequest);
        Category category = categoryRepository.getById(newProductRequest.getCategoryId());
        Product newProduct = Product.builder()
                .name(newProductRequest.getName())
                .category(category)
                .stock(0)
                .build();
        productRepository.saveAndFlush(newProduct);

        return ProductResponse.builder()
                .nameProduct(newProduct.getName())
                .nameCategory(newProduct.getCategory().getName())
                .stock(newProduct.getStock())
                .build();
    }

    @Override
    public ProductResponse getById(String id) {
        Product byIdOrThrowNotFound = findByIdOrThrowNotFound(id);
        return ProductResponse.builder()
                .nameProduct(byIdOrThrowNotFound.getName())
                .nameCategory(byIdOrThrowNotFound.getCategory().getName())
                .stock(byIdOrThrowNotFound.getStock())
                .build();
    }

    @Override
    public Product getByIdProduct(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Page<ProductResponse> getAll(SearchProductRequest request) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
    private Product findByIdOrThrowNotFound(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
    }
}
