package com.codex.codex_procurement.specification;

import com.codex.codex_procurement.dto.request.SearchCategoryRequest;
import com.codex.codex_procurement.dto.request.SearchProductRequest;
import com.codex.codex_procurement.dto.response.ProductResponse;
import com.codex.codex_procurement.entity.Category;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CategorySpecification {
    public static Specification<Category> getSpecification(SearchCategoryRequest request){
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
