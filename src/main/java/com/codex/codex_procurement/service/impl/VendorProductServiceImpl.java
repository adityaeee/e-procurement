package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.entity.VendorProduct;
import com.codex.codex_procurement.repository.VendorProductRepository;
import com.codex.codex_procurement.service.VendorProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorProductServiceImpl implements VendorProductService {

    private final VendorProductRepository vendorProductRepository;

    @Override
    public List<VendorProduct> createBulk(List<VendorProduct> vendorProducts) {
        return vendorProductRepository.saveAllAndFlush(vendorProducts);
    }

    @Override
    public VendorProduct getById(String id) {
        return vendorProductRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendor product not found"));

    }

    @Override
    public List<VendorProduct> getAll() {
        return  vendorProductRepository.findAll();
    }
}
