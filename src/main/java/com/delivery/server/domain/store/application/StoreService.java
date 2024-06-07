package com.delivery.server.domain.store.application;

import com.delivery.server.domain.order.dao.OrderRepository;
import com.delivery.server.domain.order.dto.OrderDto;
import com.delivery.server.domain.order.entity.OrderEntity;
import com.delivery.server.domain.store.dao.StoreRepository;
import com.delivery.server.domain.store.entity.StoreEntity;
import com.delivery.server.global.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;

    public PageResponse<OrderDto> getStoreOrders(Long id, Pageable pageable, LocalDate start, LocalDate end) {
        StoreEntity store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제된 가게 입니다."));

        Slice<OrderEntity> orders = orderRepository.findByStoreId(store.getId(), pageable, start, end);

        return PageResponse.of(orders.map(OrderDto::of));
    }
}
