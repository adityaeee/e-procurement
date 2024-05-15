package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.entity.VendorProduct;
import com.codex.codex_procurement.repository.VendorProductRepository;
import com.codex.codex_procurement.service.VendorProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorProductServiceImpl implements VendorProductService {

    private final VendorProductRepository vendorProductRepository;

    @Override
    public List<VendorProduct> createBulk(List<VendorProduct> vendorProducts) {
        return null;
    }
}
