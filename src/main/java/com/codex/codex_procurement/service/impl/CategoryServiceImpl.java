package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.NewCategoryRequest;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.entity.Product;
import com.codex.codex_procurement.repository.CategoryRepository;
import com.codex.codex_procurement.service.CategoryService;
import com.codex.codex_procurement.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
