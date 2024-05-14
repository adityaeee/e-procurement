package com.codex.codex_procurement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String productName;
    private String VendorName;
    private Long productPrice;
    private Integer quantity;
    private Long totalPrice;
    private Date transDate;
}
