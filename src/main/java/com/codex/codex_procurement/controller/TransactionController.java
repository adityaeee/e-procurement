package com.codex.codex_procurement.controller;

import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.constant.ResponseMessage;
import com.codex.codex_procurement.dto.request.SearchTransactionRequest;
import com.codex.codex_procurement.dto.request.TransactionRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.dto.response.PagingResponse;
import com.codex.codex_procurement.dto.response.TransactionResponse;
import com.codex.codex_procurement.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> createTransaction(
            @RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.create(request);
        CommonResponse<TransactionResponse> res = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(transactionResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<TransactionResponse>> getTransactionById(
            @PathVariable String id) {
        TransactionResponse transactionResponse = transactionService.getById(id);
        CommonResponse<TransactionResponse> res = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(transactionResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransactionResponse>>> getAllTransaction(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "vendorName") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc",required = false) String direction,
            @RequestParam(name = "date", required = false,defaultValue = "2023-05-15") @JsonFormat(pattern = "yyyy-MM-dd") String date
    ) {
        SearchTransactionRequest request = SearchTransactionRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .date(date)
                .build();

        Page<TransactionResponse> transactionAll = transactionService.getAll(request);

        PagingResponse paging = PagingResponse.builder()
                .totalPage(transactionAll.getTotalPages())
                .totalElement(transactionAll.getTotalElements())
                .page(transactionAll.getPageable().getPageNumber() + 1)
                .size(transactionAll.getPageable().getPageSize())
                .hasNext(transactionAll.hasNext())
                .hasPrevious(transactionAll.hasPrevious())
                .build();
        CommonResponse<List<TransactionResponse>> response = CommonResponse.<List<TransactionResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(transactionAll.getContent())
                .paging(paging)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

