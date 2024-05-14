package com.codex.codex_procurement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductRequest {

    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    private String name;
    private String category;
}
