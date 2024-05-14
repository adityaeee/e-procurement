package com.codex.codex_procurement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResponse {
    private String vendorName;
    private List<VendorProductResponse> vendorProductResponses;
}
