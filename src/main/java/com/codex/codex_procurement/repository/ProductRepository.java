package com.codex.codex_procurement.repository;

import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<ProductResponse> {
}
