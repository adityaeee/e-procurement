package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.NewCategoryRequest;
import com.codex.codex_procurement.dto.request.SearchCategoryRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.entity.Product;
import com.codex.codex_procurement.repository.CategoryRepository;
import com.codex.codex_procurement.service.CategoryService;
import com.codex.codex_procurement.specification.CategorySpecification;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    @Override
    public Category create(NewCategoryRequest newCategoryRequest) {
        validationUtil.validate(newCategoryRequest);
        Category newCategory = Category.builder()
                .name(newCategoryRequest.getName())
                .build();

        return categoryRepository.saveAndFlush(newCategory);
    }

    @Override
    public Category getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Page<Category> getAll(SearchCategoryRequest request) {
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

        Specification<Category> specification = CategorySpecification.getSpecification(request);

        return categoryRepository.findAll(specification, pageable);
    }



//    @Override
//    public void deleteById(String id) {
//        Category currentProduct = findByIdOrThrowNotFound(id);
//        categoryRepository.delete(currentProduct);
//    }
    private Category findByIdOrThrowNotFound(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
    }
}
