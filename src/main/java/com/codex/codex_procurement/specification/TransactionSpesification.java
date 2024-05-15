package com.codex.codex_procurement.specification;

import com.codex.codex_procurement.dto.request.SearchTransactionRequest;
import com.codex.codex_procurement.entity.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionSpesification {
    public static Specification<Transaction> getSpecification (SearchTransactionRequest request) {
        return (root, query, criteriaBuilder) -> {
//
//            Integer targetMonth = criteriaBuilder.function("MONTH", Integer.class, root.get("date"));
//            Integer targetYear = criteriaBuilder.function("YEAR", Integer.class, root.get("date"));

            List<Predicate> predicates = new ArrayList<>();

            if (request.getDate() != null) {
                if (request.getMonth()) {
                    Predicate monthPredicate = criteriaBuilder.equal(criteriaBuilder.function("MONTH", Integer.class, root.get("date")), request.getDate());
                    Predicate yearPredicate = criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class, root.get("date")), request.getDate());
                    predicates.add(criteriaBuilder.and(monthPredicate, yearPredicate));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get("date"), request.getDate()));
                }
            }
//
//            if (request.getDate() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("date"), request.getDate()));
//
//            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
