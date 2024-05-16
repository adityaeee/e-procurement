package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.NewProductRequest;
import com.codex.codex_procurement.dto.request.SearchProductRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.entity.Product;
import com.codex.codex_procurement.repository.CategoryRepository;
import com.codex.codex_procurement.repository.ProductRepository;
import com.codex.codex_procurement.service.CategoryService;
import com.codex.codex_procurement.service.ProductService;
import com.codex.codex_procurement.specification.ProductSpecification;
import com.codex.codex_procurement.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ValidationUtil validationUtil;
    @Override
    public ProductResponse create(NewProductRequest newProductRequest) {
        validationUtil.validate(newProductRequest);
        Category category = categoryService.getById(newProductRequest.getCategoryId());
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

        if (request.getPage() <= 0 ){
            request.setPage(1);
        }
        String validSortBy;
        if ("name".equalsIgnoreCase(request.getSortBy())){
            validSortBy = request.getSortBy();
        } else {
            validSortBy = "name";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()),validSortBy);

        Pageable pageable = PageRequest.of((request.getPage() - 1), request.getSize(), sort);

        Specification<ProductResponse> specification = ProductSpecification.getSpecification(request);

        return productRepository.findAll(specification, pageable);

    }

    @Override
    public Product update(Product req) {
//        findByIdOrThrowNotFound(req.getId());
        Product fund = findByIdOrThrowNotFound(req.getId());

        Product update = Product.builder()
                .id(fund.getId())
                .name(req.getName())
                .stock(fund.getStock())
                .category(fund.getCategory())
                .build();
        return productRepository.saveAndFlush(update);
    }

    @Override
    public void deleteById(String id) {
        Product currentProduct = findByIdOrThrowNotFound(id);
        productRepository.delete(currentProduct);
    }
    private Product findByIdOrThrowNotFound(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
    }
}
