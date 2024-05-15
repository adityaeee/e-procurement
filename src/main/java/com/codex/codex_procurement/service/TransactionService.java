package com.codex.codex_procurement.service;

import com.codex.codex_procurement.dto.request.SearchTransactionRequest;
import com.codex.codex_procurement.dto.request.TransactionRequest;
import com.codex.codex_procurement.dto.response.TransactionResponse;
import com.codex.codex_procurement.entity.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    TransactionResponse getById(String id);
    Page<TransactionResponse> getAll(SearchTransactionRequest request);
//    List<TransactionResponse> getAllTest();
}
