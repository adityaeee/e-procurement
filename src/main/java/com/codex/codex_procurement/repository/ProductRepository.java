package com.codex.codex_procurement.repository;

import com.codex.codex_procurement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
