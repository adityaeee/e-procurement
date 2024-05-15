package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.SearchVendorRequest;
import com.codex.codex_procurement.dto.request.VendorRequest;
import com.codex.codex_procurement.dto.response.VendorResponse;
import com.codex.codex_procurement.entity.Vendor;
import com.codex.codex_procurement.repository.VendorProductRepository;
import com.codex.codex_procurement.repository.VendorRepository;
import com.codex.codex_procurement.service.VendorProductService;
import com.codex.codex_procurement.service.VendorService;
import com.codex.codex_procurement.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorProductRepository vendorProductRepository;
    private VendorProductService vendorProductService;
    private ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public VendorResponse create(VendorRequest vendorRequest) {

        return null;
    }

    @Override
    public Vendor getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public VendorResponse getByIdListProduct(String id) {
        return null;
    }

    @Override
    public Page<Vendor> getAll(SearchVendorRequest vendorRequest) {
        return null;
    }

    @Override
    public Vendor update(Vendor vendor) {
        findByIdOrThrowNotFound(vendor.getId());
        return vendorRepository.saveAndFlush(vendor);
    }

    @Override
    public void delete(String id) {
        Vendor vendor = findByIdOrThrowNotFound(id);
        vendorRepository.delete(vendor);

    }

    public Vendor findByIdOrThrowNotFound(String id){
        return vendorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }
}
