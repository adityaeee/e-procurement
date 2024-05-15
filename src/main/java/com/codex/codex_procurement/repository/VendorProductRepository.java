package com.codex.codex_procurement.repository;

import com.codex.codex_procurement.entity.Vendor;
import com.codex.codex_procurement.entity.VendorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorProductRepository extends JpaRepository<VendorProduct,String> {
}
