package com.delivery.server.domain.order.dao;

import com.delivery.server.domain.order.entity.OrderOptionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOptionDetailRepository extends JpaRepository<OrderOptionDetailEntity, Long> {
}
