package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.fulfill.entity.Ongoing;

public interface OngoingRepository extends JpaRepository<Ongoing, String> {

}
