package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.UpdatePriceRequest;
import com.codex.codex_procurement.entity.VendorProduct;
import com.codex.codex_procurement.repository.VendorProductRepository;
import com.codex.codex_procurement.service.VendorProductService;
import com.codex.codex_procurement.service.VendorService;
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

    @Override
    public VendorProduct update(UpdatePriceRequest request) {
        VendorProduct byVendorIdAndProductId = vendorProductRepository.findByVendorIdAndProductId(request.getVendorId(), request.getProductId());
        VendorProduct updatePrice = VendorProduct.builder()
                .id(byVendorIdAndProductId.getId())
                .product(byVendorIdAndProductId.getProduct())
                .vendor(byVendorIdAndProductId.getVendor())
                .price(request.getPrice())
                .build();
        return vendorProductRepository.saveAndFlush(updatePrice);
//        List<VendorProduct> listVendorProduct = vendorProductRepository.findAll();
//        listVendorProduct.stream().filter().map(
//                detail ->{
//                    return detail.getProduct().getId().equals(request.getProductId()) && detail.getVendor().getId().equals(request.getVendorId()); //&& detail.getProduct().getId().equals(vendorProductRepository.findById(request.getProductId())) ;
//                }
//        ).toList();
//        vendorProductRepository.findById().orElseThrow(()-> new RuntimeException("Vendor and product not found"));
//        vendorProductRepository.saveAndFlush()

    }
}
