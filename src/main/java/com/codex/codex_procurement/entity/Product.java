package com.codex.codex_procurement.entity;

import com.codex.codex_procurement.constant.ConstantTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.PRODUCT)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "stock", columnDefinition = "INT CHECK (stock >= 0)")
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    @JsonIgnore
    private List<VendorProduct> productVendors;
}
