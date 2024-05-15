package com.codex.codex_procurement.controller;


import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.constant.ResponseMessage;
import com.codex.codex_procurement.dto.request.SearchVendorRequest;
import com.codex.codex_procurement.dto.request.VendorRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.dto.response.PagingResponse;
import com.codex.codex_procurement.dto.response.VendorResponse;
import com.codex.codex_procurement.entity.Vendor;
import com.codex.codex_procurement.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.VENDOR_API)
public class VendorController {

    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<CommonResponse<VendorResponse>> createNewVendor(
            @RequestBody VendorRequest vendorRequest
            ){
        VendorResponse newVendor = vendorService.create(vendorRequest);
        CommonResponse<VendorResponse> response = CommonResponse.<VendorResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfuly create new vendor")
                .data(newVendor)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<VendorResponse>> findById(@PathVariable String id){
        VendorResponse vendor = vendorService.getByIdListProduct(id);
        CommonResponse<VendorResponse> response = CommonResponse.<VendorResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(vendor)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Vendor>>> getAllVendor(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ){
        SearchVendorRequest request = SearchVendorRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();

        Page<Vendor> allVendor = vendorService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPage(allVendor.getTotalPages())
                .totalElement(allVendor.getTotalElements())
                .page(allVendor.getPageable().getPageNumber() + 1)
                .size(allVendor.getPageable().getPageSize())
                .hasNext(allVendor.hasNext())
                .hasPrevious(allVendor.hasPrevious())
                .build();

        CommonResponse<List<Vendor>> response = CommonResponse.<List<Vendor>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get all products")
                .data(allVendor.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }


}
