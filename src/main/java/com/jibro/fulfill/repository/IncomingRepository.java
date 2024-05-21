package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Incoming;

import java.util.List;

@Repository
public interface IncomingRepository extends JpaRepository<Incoming, String> {
   /* @Query("select sum(i.incomingCount) from Incoming i where i.product = :product")
    Long getTotalQuantityByProduct(Incoming incoming);*/
}
