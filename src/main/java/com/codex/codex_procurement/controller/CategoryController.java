package com.codex.codex_procurement.controller;

import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.dto.request.NewCategoryRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CATEGORY_API)
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CommonResponse<Category>> createNewCategory(
            @RequestBody NewCategoryRequest categoryRequest
    ){
        Category newCategory = categoryService.create(categoryRequest);
        CommonResponse<Category> response = CommonResponse.<Category>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("sucessfuly create new category")
                .data(newCategory)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
