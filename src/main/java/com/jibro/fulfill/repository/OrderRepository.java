package com.jibro.fulfill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {

	@Query("SELECT o.product.productId, o.orderDate, COUNT(o) " +
	           "FROM Order o " +
	           "GROUP BY o.product.productId, o.orderDate")
    List<Object[]> findProductOrdersGroupedByProductAndDate();
}
