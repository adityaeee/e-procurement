package com.codex.codex_procurement.controller;


import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.dto.request.NewVendorRequest;
import com.codex.codex_procurement.dto.request.VendorRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.dto.response.VendorResponse;
import com.codex.codex_procurement.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
