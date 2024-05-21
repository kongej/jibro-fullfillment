package com.jibro.fulfill.specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.jibro.fulfill.entity.Order;

public class OrderSpecification {

    public static Specification<Order> getOrders(String orderId, LocalDateTime fromDate, LocalDateTime toDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (orderId != null && !orderId.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("orderId"), orderId));
            }

            if (fromDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("orderDate"), fromDate));
            }

            if (toDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("orderDate"), toDate));
            }

            query.orderBy(criteriaBuilder.desc(root.get("orderDate")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
