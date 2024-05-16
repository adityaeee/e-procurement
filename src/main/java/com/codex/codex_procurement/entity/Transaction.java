package com.codex.codex_procurement.entity;

import com.codex.codex_procurement.constant.ConstantTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.TRANSACTION)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "quantity",nullable = false,columnDefinition = "INT CHECK (quantity > 0)")
    private Integer quantity;

    @Column(name = "total_price",nullable = false,columnDefinition = "BIGINT CHECK (total_price >= 0)")
    private Long totalPrice;

    @Temporal(TemporalType.DATE)
    @Column(name = "trans_date", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transDate;

    @ManyToOne
    @JoinColumn(name = "vendor_product_id")
    @JsonBackReference
    private VendorProduct vendorProduct;
}
