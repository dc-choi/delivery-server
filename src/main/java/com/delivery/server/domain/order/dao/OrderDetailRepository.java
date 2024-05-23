package com.delivery.server.domain.order.dao;

import com.delivery.server.domain.order.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
