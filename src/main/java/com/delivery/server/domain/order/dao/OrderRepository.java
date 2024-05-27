package com.delivery.server.domain.order.dao;

import com.delivery.server.domain.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    public List<OrderEntity> findByStoreId(Long storeId);
}
