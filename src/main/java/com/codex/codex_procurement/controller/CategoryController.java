package com.codex.codex_procurement.controller;

import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.constant.ResponseMessage;
import com.codex.codex_procurement.dto.request.NewCategoryRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Category>> getByIdCategory(@PathVariable String id) {
        Category categoryById = categoryService.getById(id);
        CommonResponse<Category> response = CommonResponse.<Category>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(categoryById)
                .build();
        return ResponseEntity.ok(response);
    }
}
