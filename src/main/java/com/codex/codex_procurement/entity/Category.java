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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.CATEGORY)
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private String id;

   @Column(name = "name",nullable = false)
   private String name;

   @OneToMany(mappedBy = "category")
   @JsonManagedReference
   @JsonIgnore
   private List<Product> products;
}
