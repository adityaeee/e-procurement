package com.codex.codex_procurement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorRequest {
    private String vendorId;
    private List<VendorProductRequest> vendorProductRequests;
}
