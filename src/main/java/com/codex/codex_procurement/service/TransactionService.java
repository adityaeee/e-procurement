package com.codex.codex_procurement.service;

import com.codex.codex_procurement.dto.request.SearchTransactionRequest;
import com.codex.codex_procurement.dto.request.TransactionRequest;
import com.codex.codex_procurement.dto.response.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
    TransactionResponse getById(String id);
    Page<TransactionResponse> getAll(SearchTransactionRequest request);
}
