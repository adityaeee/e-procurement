package com.codex.codex_procurement.controller;

import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.constant.ResponseMessage;
import com.codex.codex_procurement.dto.request.NewCategoryRequest;
import com.codex.codex_procurement.dto.request.SearchCategoryRequest;
import com.codex.codex_procurement.dto.request.SearchProductRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.dto.response.PagingResponse;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Category;
import com.codex.codex_procurement.entity.Product;
import com.codex.codex_procurement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<CommonResponse<List<Category>>> getAllProduct(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ) {


        SearchCategoryRequest request = SearchCategoryRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();
        Page<Category> categoryAll = categoryService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPage(categoryAll.getTotalPages())
                .totalElement(categoryAll.getTotalElements())
                .page(categoryAll.getPageable().getPageNumber() + 1)
                .size(categoryAll.getPageable().getPageSize())
                .hasNext(categoryAll.hasNext())
                .hasPrevious(categoryAll.hasPrevious())
                .build();

        CommonResponse<List<Category>> response = CommonResponse.<List<Category>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get all product")
                .data(categoryAll.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
