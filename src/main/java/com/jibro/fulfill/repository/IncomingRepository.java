package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.fulfill.entity.Incoming;

public interface IncomingRepository extends JpaRepository<Incoming, String> {

}
