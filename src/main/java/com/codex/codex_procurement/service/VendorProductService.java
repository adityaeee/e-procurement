package com.codex.codex_procurement.service;

import com.codex.codex_procurement.entity.VendorProduct;

import java.util.List;

public interface VendorProductService {
    List<VendorProduct> createBulk (List<VendorProduct> vendorProducts);
    VendorProduct getById (String id);
    List<VendorProduct> getAll();
}
