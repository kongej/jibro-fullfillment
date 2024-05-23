package com.jibro.fulfill.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Incoming;

@Repository
public interface IncomingRepository extends JpaRepository<Incoming, String> {
	public Page<Incoming> findAll(Pageable pageable);
	public Page<Incoming> findByIncomingIdContains(String searchId, Pageable pageable);
	public Page<Incoming> findByProductProductIdContains(String searchId, Pageable pageable);
	public Page<Incoming> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	
	@Query("SELECT SUM(i.incomingCount) FROM Incoming i WHERE i.orderStatus = 1 AND i.updatedAt BETWEEN :startOfDay AND :endOfDay")
    Integer findTotalIncomingCountForToday(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
