package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Ongoing;

@Repository
public interface OngoingRepository extends JpaRepository<Ongoing, String> {

}
