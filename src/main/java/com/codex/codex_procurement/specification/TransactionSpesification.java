package com.codex.codex_procurement.specification;

import com.codex.codex_procurement.dto.request.SearchTransactionRequest;
import com.codex.codex_procurement.entity.Transaction;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionSpesification {
    public static Specification<Transaction> getSpecification (SearchTransactionRequest request) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



            if (request.getDate() != null && request.getEndDate() != null){
                try{
                    LocalDate parseDate = LocalDate.parse(request.getDate(), dateTimeFormatter);
                    LocalDate parseEndDate = LocalDate.parse(request.getEndDate(), dateTimeFormatter);
                    predicates.add(criteriaBuilder.between(root.get("transDate"), parseDate, parseEndDate));
                }catch (Exception e){
                    throw new RuntimeException("ERROR PARSING DATE");
                }

                return query.where(predicates.toArray(new Predicate[]{})).getRestriction();

            }

            if (request.getDate() != null) {
                try{
                    LocalDate parseDate = LocalDate.parse(request.getDate(), dateTimeFormatter);
                    Predicate datePredicate = criteriaBuilder.equal(root.get("transDate"), parseDate);
                    predicates.add(datePredicate);

                }catch (Exception e){
                    throw new RuntimeException("ERROR PARSING DATE");
                }
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
