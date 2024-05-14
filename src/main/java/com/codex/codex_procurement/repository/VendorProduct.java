package com.codex.codex_procurement.repository;

import com.codex.codex_procurement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorProduct extends JpaRepository<Vendor,String> {
}
