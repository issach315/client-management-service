package com.clientmanagement.specification;

import com.clientmanagement.dto.request.ClientFilterParams;
import com.clientmanagement.entity.Client;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ClientSpecification {

    public static Specification<Client> filterClients(ClientFilterParams f) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // ── Global search across multiple fields ─────────────────────────
            if (hasValue(f.getSearch())) {
                String pattern = "%" + f.getSearch().toLowerCase().trim() + "%";

                Predicate globalSearch = cb.or(
                        cb.like(cb.lower(root.get("clientName")), pattern),
                        cb.like(cb.lower(root.get("email")),      pattern),
                        cb.like(cb.lower(root.get("industry")),   pattern),
                        cb.like(cb.lower(root.get("location")),   pattern),
                        cb.like(cb.lower(root.get("status")),     pattern)
                );
                predicates.add(globalSearch);
            }

            // ── Field-specific filters (AND'd together) ──────────────────────
            if (hasValue(f.getClientName())) {
                predicates.add(cb.like(
                        cb.lower(root.get("clientName")),
                        "%" + f.getClientName().toLowerCase().trim() + "%"));
            }
            if (hasValue(f.getEmail())) {
                predicates.add(cb.equal(root.get("email"), f.getEmail().trim()));
            }
            if (hasValue(f.getPhoneNumber())) {
                predicates.add(cb.equal(root.get("phoneNumber"), f.getPhoneNumber().trim()));
            }
            if (hasValue(f.getIndustry())) {
                predicates.add(cb.equal(root.get("industry"), f.getIndustry().trim()));
            }
            if (hasValue(f.getStatus())) {
                predicates.add(cb.equal(root.get("status"), f.getStatus().trim()));
            }
            if (hasValue(f.getLocation())) {
                predicates.add(cb.like(
                        cb.lower(root.get("location")),
                        "%" + f.getLocation().toLowerCase().trim() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static boolean hasValue(String s) {
        return s != null && !s.isBlank();
    }
}