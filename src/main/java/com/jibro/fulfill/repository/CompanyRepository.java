package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.fulfill.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, String> {

}
