package com.codex.codex_procurement.service;

import com.codex.codex_procurement.dto.request.NewVendorRequest;
import com.codex.codex_procurement.dto.request.SearchVendorRequest;
import com.codex.codex_procurement.dto.request.VendorRequest;
import com.codex.codex_procurement.dto.response.VendorResponse;
import com.codex.codex_procurement.entity.Vendor;
import org.springframework.data.domain.Page;

import java.util.List;


public interface VendorService {
    VendorResponse create(VendorRequest vendorRequest);
    Vendor getById(String id);
    VendorResponse getByIdListProduct (String id);
    Page<Vendor> getAll(SearchVendorRequest vendorRequest);
    Vendor update(Vendor vendor);
    void delete(String id);


}
