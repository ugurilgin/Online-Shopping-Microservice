package com.ugisoftware.orderservice.dataAccess;

import com.ugisoftware.orderservice.entities.concretes.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
