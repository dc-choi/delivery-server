package com.delivery.server.domain.store.dao;

import com.delivery.server.domain.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
}
