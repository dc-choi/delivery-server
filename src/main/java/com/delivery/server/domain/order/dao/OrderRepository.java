package com.delivery.server.domain.order.dao;

import com.delivery.server.domain.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, OrderRepositoryCustom {
}
