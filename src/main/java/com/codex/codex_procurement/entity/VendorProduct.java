package com.codex.codex_procurement.entity;

import com.codex.codex_procurement.constant.ConstantTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = ConstantTable.VENDOR_PRODUCT)
public class VendorProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonBackReference
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Column(name = "price")
    private Long price;

}
