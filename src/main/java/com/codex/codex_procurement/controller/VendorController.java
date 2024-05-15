package com.codex.codex_procurement.controller;


import com.codex.codex_procurement.constant.APIUrl;
import com.codex.codex_procurement.constant.ResponseMessage;
import com.codex.codex_procurement.dto.request.UpdatePriceRequest;
import com.codex.codex_procurement.dto.request.VendorRequest;
import com.codex.codex_procurement.dto.response.CommonResponse;
import com.codex.codex_procurement.dto.response.VendorProductResponse;
import com.codex.codex_procurement.dto.response.VendorResponse;
import com.codex.codex_procurement.entity.Vendor;
import com.codex.codex_procurement.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "/updatePrice")
    public ResponseEntity<CommonResponse<VendorProductResponse>> updatePrice(
            @RequestBody UpdatePriceRequest updatePriceRequest
            ){
        VendorProductResponse vendorProductResponse = vendorService.updatePrice(updatePriceRequest);
        CommonResponse<VendorProductResponse> response = CommonResponse.<VendorProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(vendorProductResponse)
                .build();
        return ResponseEntity.ok(response);

    }



}
