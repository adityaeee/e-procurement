package com.codex.codex_procurement.repository;

import com.codex.codex_procurement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorProductRepository extends JpaRepository<Vendor,String> {
}
