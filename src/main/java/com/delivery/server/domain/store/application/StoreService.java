package com.delivery.server.domain.store.application;

import com.delivery.server.domain.order.dao.OrderRepository;
import com.delivery.server.domain.order.dto.OrderDto;
import com.delivery.server.domain.store.dao.StoreRepository;
import com.delivery.server.domain.store.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;

    public List<OrderDto> getStoreOrders(Long id) {
        StoreEntity store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제된 가게 입니다."));

        return orderRepository.findByStoreId(store.getId()).stream()
                .map(OrderDto::of)
                .toList();
    }
}
