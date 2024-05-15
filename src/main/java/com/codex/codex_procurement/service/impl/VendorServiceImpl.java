package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.SearchVendorRequest;
import com.codex.codex_procurement.dto.request.UpdatePriceRequest;
import com.codex.codex_procurement.dto.request.VendorProductRequest;
import com.codex.codex_procurement.dto.request.VendorRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.dto.response.VendorProductResponse;
import com.codex.codex_procurement.dto.response.VendorResponse;
import com.codex.codex_procurement.entity.Product;
import com.codex.codex_procurement.entity.Vendor;
import com.codex.codex_procurement.entity.VendorProduct;
import com.codex.codex_procurement.repository.VendorProductRepository;
import com.codex.codex_procurement.repository.VendorRepository;
import com.codex.codex_procurement.service.ProductService;
import com.codex.codex_procurement.service.VendorProductService;
import com.codex.codex_procurement.service.VendorService;
import com.codex.codex_procurement.specification.VendorSpecification;
import com.codex.codex_procurement.utils.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorProductRepository vendorProductRepository;
    private final VendorProductService vendorProductService;
    private final ProductService productService;
    private final ValidationUtil validationUtil;


    @Transactional(rollbackOn = Exception.class)
    @Override
    public VendorResponse create(VendorRequest vendorRequest) {
        Vendor vendor = Vendor.builder()
                .name(vendorRequest.getVendorName())
                .build();

        vendorRepository.saveAndFlush(vendor);

        List<VendorProduct> vendorProducts = vendorRequest.getVendorProductRequests().stream()
                .map(vendorProductRequest -> {
                    Product product = productService.getByIdProduct(vendorProductRequest.getProductId());

                    product.setStock(product.getStock() + vendorProductRequest.getStock());

                    return VendorProduct.builder()
                            .vendor(vendor)
                            .product(product)
                            .price(vendorProductRequest.getPrice())
                            .build();
                }).toList();

        vendorProductService.createBulk(vendorProducts);
        vendor.setVendorProducts(vendorProducts);

        List<VendorProductResponse> responses = vendorProducts.stream()
                .map(res -> {
                    return VendorProductResponse.builder()
                            .nameProduct(res.getProduct().getName())
                            .price(res.getPrice())
                            .build();
                }).toList();

        return VendorResponse.builder()
                .vendorName(vendor.getName())
                .vendorProductResponses(responses)
                .build();
    }

    @Override
    public Vendor getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public VendorResponse getByIdListProduct(String id) {
        Vendor vendor = findByIdOrThrowNotFound(id);

        List<VendorProduct> vendorProducts = vendorProductService.getAll();


        List<VendorProduct> productVendor = vendorProducts.stream().filter(
                product -> product.getVendor().getId().equals(vendor.getId())
        ).toList();

        List<VendorProductResponse> vendorProductResponses = productVendor.stream()
                .map(vendorProduct -> {
                    return VendorProductResponse.builder()
                            .nameProduct(vendorProduct.getProduct().getName())
                            .price(vendorProduct.getPrice())
                            .build();
                }).toList();

        return VendorResponse.builder()
                .vendorName(vendor.getName())
                .vendorProductResponses(vendorProductResponses)
                .build();

    }

    @Override
    public Page<Vendor> getAll(SearchVendorRequest request) {
        if(request.getPage() <= 0) {
            request.setPage(1);
        }

        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());

        Pageable pageable = PageRequest.of(request.getPage() -1, request.getSize(), sort);

        Specification<Vendor> specification = VendorSpecification.getSpecification(request);

        return vendorRepository.findAll(specification, pageable);
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

    @Override
    public VendorProductResponse updatePrice(UpdatePriceRequest request) {
        VendorProduct update = vendorProductService.update(request);
        VendorProductResponse response = VendorProductResponse.builder()
                .nameProduct(update.getProduct().getName())
                .price(update.getPrice())
                .build();

        return response;
    }
}
