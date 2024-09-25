package com.project.start.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.start.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> {

}
