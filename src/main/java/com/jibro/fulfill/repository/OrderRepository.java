package com.jibro.fulfill.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.jibro.fulfill.dto.sales.SalesSummaryResponseDto;
import com.jibro.fulfill.entity.Order;
import com.jibro.fulfill.entity.Product;

@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {

	@Query("SELECT o.product, FUNCTION('DATE', o.orderDate), SUM(o.count) " +
	           "FROM Order o " +
	           "WHERE (:from IS NULL OR o.orderDate >= :from) " +
	           "  AND (:to IS NULL OR o.orderDate <= :to) " +
	           "GROUP BY o.product, FUNCTION('DATE', o.orderDate)" +
			   "ORDER BY FUNCTION('DATE', o.orderDate) DESC")
    Page<Object[]> findOrdersGroupedByProductAndDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, Pageable pageable);
    
    @Query("SELECT o.product, FUNCTION('DATE', o.orderDate), SUM(o.count) " +
            "FROM Order o " +
            "WHERE (:from IS NULL OR o.orderDate >= :from) " +
            "  AND (:to IS NULL OR o.orderDate <= :to) " +
            "GROUP BY o.product, FUNCTION('DATE', o.orderDate) " +
            "ORDER BY FUNCTION('DATE', o.orderDate) DESC")
     List<Object[]> findOrdersGroupedByProductAndDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
     
     @Query("SELECT COUNT(o) FROM Order o WHERE o.orderStatus = 0")
     Integer countNewOrder();
     
    default Page<SalesSummaryResponseDto> findOrderSummaries(LocalDateTime from, LocalDateTime to, Pageable pageable) {
        Page<Object[]> results = findOrdersGroupedByProductAndDate(from, to, pageable);
         
        List<SalesSummaryResponseDto> dtos = results.stream()
					                    .map(result -> new SalesSummaryResponseDto(
					                    		(Product) result[0], 
					                  		  ((java.sql.Date) result[1]).toLocalDate(), 
						                      ((Number) result[2]).intValue()))
						                      .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, results.getTotalElements());
    }

    default List<SalesSummaryResponseDto> findOrderSummariesEmailContent(LocalDateTime from, LocalDateTime to) {
        List<Object[]> results = findOrdersGroupedByProductAndDate(from, to);

        return results.stream()
                .map(result -> new SalesSummaryResponseDto(
                		(Product) result[0],
                        ((java.sql.Date) result[1]).toLocalDate(),
                        ((Number) result[2]).intValue()))
                .collect(Collectors.toList());
    }
 
    
}
