package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jibro.fulfill.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
