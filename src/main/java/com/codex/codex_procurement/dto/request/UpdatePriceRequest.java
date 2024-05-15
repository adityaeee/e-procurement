package com.codex.codex_procurement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePriceRequest {
    private String vendorId;
    private String productId;

    @NotNull(message = "quantity is required")
    @Min(value = 0, message = "stock must be greater than or equal 1")
    private Long price;
}
