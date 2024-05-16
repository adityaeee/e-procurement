package com.codex.codex_procurement.specification;

import com.codex.codex_procurement.dto.request.SearchProductRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import jakarta.persistence.criteria.Predicate;
import com.codex.codex_procurement.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ProductSpecification {
    public static Specification<ProductResponse> getSpecification(SearchProductRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(request.getName() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + request.getName().toLowerCase() + "%"
                        )
                );
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
