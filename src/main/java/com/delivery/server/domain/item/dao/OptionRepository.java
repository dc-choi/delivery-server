package com.delivery.server.domain.item.dao;

import com.delivery.server.domain.item.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
}
