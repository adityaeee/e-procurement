package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.SearchVendorRequest;
import com.codex.codex_procurement.dto.request.VendorProductRequest;
import com.codex.codex_procurement.dto.request.VendorRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.dto.response.VendorResponse;
import com.codex.codex_procurement.entity.Vendor;
import com.codex.codex_procurement.entity.VendorProduct;
import com.codex.codex_procurement.repository.VendorProductRepository;
import com.codex.codex_procurement.repository.VendorRepository;
import com.codex.codex_procurement.service.ProductService;
import com.codex.codex_procurement.service.VendorProductService;
import com.codex.codex_procurement.service.VendorService;
import com.codex.codex_procurement.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorProductRepository vendorProductRepository;
    private VendorProductService vendorProductService;
    private ProductService productService;
    private ValidationUtil validationUtil;


    @Override
    public VendorResponse create(VendorRequest vendorRequest) {
        Vendor vendor = Vendor.builder()
                .name(vendorRequest.getVendorName())
                .build();

        vendorRepository.saveAndFlush(vendor);

        List<VendorProduct> vendorProducts = vendorRequest.getVendorProductRequests().stream()
                .map(vendorProductRequest -> {
                    ProductResponse product = productService.getById(vendorProductRequest.getProductId());

                }).toList();


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

    private Vendor findByIdOrThrowNotFound(String id){
        return vendorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendor not found"));
    }
}
