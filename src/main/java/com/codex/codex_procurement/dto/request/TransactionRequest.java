package com.codex.codex_procurement.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    private String vendorProductId;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "stock must be greater than or equal 1")
    private Integer quantity;

}
