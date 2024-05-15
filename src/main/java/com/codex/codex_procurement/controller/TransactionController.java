package com.codex.codex_procurement.controller;

import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.constant.ResponseMessage;
import com.codex.codex_procurement.dto.request.TransactionRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.dto.response.TransactionResponse;
import com.codex.codex_procurement.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> createTransaction(
            @RequestBody TransactionRequest request){
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
            @PathVariable String id){
        TransactionResponse transactionResponse = transactionService.getById(id);
        CommonResponse<TransactionResponse> res = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(transactionResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
