package com.jibro.fulfill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jibro.fulfill.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
