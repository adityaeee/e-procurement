package com.codex.codex_procurement.repository;

import com.codex.codex_procurement.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
