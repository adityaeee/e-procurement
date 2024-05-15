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
@Builder
@Entity
@Table(name = ConstantTable.VENDOR)
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vendor")
    @JsonManagedReference
    @JsonIgnore
    private List<VendorProduct> vendorProducts;

}
