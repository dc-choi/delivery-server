package com.delivery.server.domain.order.dao;

import com.delivery.server.domain.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrderRepositoryCustom {
    Page<OrderEntity> findByStoreId(Long storeId, Pageable pageable, LocalDate start, LocalDate end);
}
