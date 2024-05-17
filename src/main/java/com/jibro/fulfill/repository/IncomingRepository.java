package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Incoming;

@Repository
public interface IncomingRepository extends JpaRepository<Incoming, String> {

}
