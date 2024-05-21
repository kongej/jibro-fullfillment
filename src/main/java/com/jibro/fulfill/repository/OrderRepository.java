package com.jibro.fulfill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

	@Query("SELECT o.product.productId, o.orderedDate, COUNT(o) " +
	           "FROM Order o " +
	           "GROUP BY o.product.productId, o.orderedDate")
    List<Object[]> findProductOrdersGroupedByProductAndDate();
}
