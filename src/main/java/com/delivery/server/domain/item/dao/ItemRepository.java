package com.delivery.server.domain.item.dao;

import com.delivery.server.domain.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
