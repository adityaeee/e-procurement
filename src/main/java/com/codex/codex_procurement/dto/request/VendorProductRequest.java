package com.codex.codex_procurement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorProductRequest {

    private String productId;
    private Long price;
    private Integer stock;
}
