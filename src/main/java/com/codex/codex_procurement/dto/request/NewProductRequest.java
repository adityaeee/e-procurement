package com.codex.codex_procurement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewProductRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Category is required")
    private String category_id;
}
