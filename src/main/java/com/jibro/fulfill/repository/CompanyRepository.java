package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

}
