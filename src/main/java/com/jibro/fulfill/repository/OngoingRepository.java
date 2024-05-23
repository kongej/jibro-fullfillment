package com.jibro.fulfill.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Ongoing;

@Repository
public interface OngoingRepository extends JpaRepository<Ongoing, String> {
	public Page<Ongoing> findAll(Pageable pageable);
	public Page<Ongoing> findByOngoingIdContains(String searchId, Pageable pageable);
	public Page<Ongoing> findByOrderProductProductIdContains(String searchId, Pageable pageable);
	public Page<Ongoing> findByOrderOrderIdContains(String searchId, Pageable pageable);
	public Page<Ongoing> findByInvcContains(Integer searchId, Pageable pageable);
	@Query("SELECT o FROM Ongoing o WHERE CAST(o.invc AS string) LIKE %:searchId%")
	public Page<Ongoing> findByInvcContainingOne(@Param("searchId") String searchId, Pageable pageable);
	public Page<Ongoing> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	
	@Query("SELECT COUNT(o) FROM Ongoing o WHERE o.createdAt BETWEEN :startOfDay AND :endOfDay")
    Integer findTotalOngoingCountForToday(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
