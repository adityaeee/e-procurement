package com.codex.codex_procurement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class VendorProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private Long price;

}
