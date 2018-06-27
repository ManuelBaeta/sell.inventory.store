package com.manolo.sell.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manolo.sell.inventory.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
