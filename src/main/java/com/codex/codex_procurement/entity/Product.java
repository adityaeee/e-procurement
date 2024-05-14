package com.codex.codex_procurement.entity;

import com.codex.codex_procurement.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "stock", nullable = false, columnDefinition = "INT CHECK (stock >= 0)")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
