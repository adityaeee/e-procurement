package com.codex.codex_procurement.service.impl;

import com.codex.codex_procurement.dto.request.SearchTransactionRequest;
import com.codex.codex_procurement.dto.request.TransactionRequest;
import com.codex.codex_procurement.dto.response.TransactionResponse;
import com.codex.codex_procurement.entity.Product;
import com.codex.codex_procurement.entity.Transaction;
import com.codex.codex_procurement.entity.Vendor;
import com.codex.codex_procurement.entity.VendorProduct;
import com.codex.codex_procurement.repository.TransactionRepository;
import com.codex.codex_procurement.service.ProductService;
import com.codex.codex_procurement.service.TransactionService;
import com.codex.codex_procurement.service.VendorProductService;
import com.codex.codex_procurement.service.VendorService;
import com.codex.codex_procurement.utils.ValidationUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Data
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final VendorProductService vendorProductService;
    private final VendorService vendorService;
    private final ProductService productService;
    private final ValidationUtil validationUtil;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionResponse create(TransactionRequest request) {
        validationUtil.validate(request);
        VendorProduct vendorProduct = vendorProductService.getById(request.getVendorProductId());
        System.out.println("==========================");
        log.info("Vendor product" + request.toString());
        Product product = vendorProduct.getProduct();
        Vendor vendor = vendorProduct.getVendor();

        Transaction trx = Transaction.builder()
                .vendorProduct(vendorProduct)
                .quantity(request.getQuantity())
                .totalPrice(vendorProduct.getPrice() * request.getQuantity())
                .transDate(new Date())
                .build();
        //save ke database
        transactionRepository.saveAndFlush(trx);

        //menyiapkan response ke controller
        TransactionResponse response = TransactionResponse.builder()
                .productName(product.getName())
                .VendorName(vendor.getName())
                .productPrice(vendorProduct.getPrice())
                .quantity(trx.getQuantity())
                .transDate(trx.getTransDate())
                .totalPrice(trx.getTotalPrice())
                .build();

        return response;
    }

    @Override
    public TransactionResponse getById(String id) {
        Transaction trx = getByIdOrThrowNotFound(id);
        VendorProduct vendorProduct = vendorProductService.getById(trx.getVendorProduct().getId());
        Product product = vendorProduct.getProduct();
        Vendor vendor = vendorProduct.getVendor();


        TransactionResponse response = TransactionResponse.builder()
                .productName(product.getName())
                .VendorName(vendor.getName())
                .productPrice(vendorProduct.getPrice())
                .quantity(trx.getQuantity())
                .transDate(trx.getTransDate())
                .totalPrice(trx.getTotalPrice())
                .build();
        return response;
    }

    public Transaction getByIdOrThrowNotFound(String id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }


    @Override
    public Page<TransactionResponse> getAll(SearchTransactionRequest request) {
        return null;
    }
}
