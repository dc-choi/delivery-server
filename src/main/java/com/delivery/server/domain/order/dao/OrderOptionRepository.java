package com.delivery.server.domain.order.dao;

import com.delivery.server.domain.order.entity.OrderOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOptionRepository extends JpaRepository<OrderOptionEntity, Long> {
}
