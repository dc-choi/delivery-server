package com.delivery.server.domain.order.dto;

import com.delivery.server.domain.item.dto.ItemDto;
import com.delivery.server.domain.order.entity.OrderEntity;
import com.delivery.server.domain.store.dto.StoreDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderDto {
    private Long id;
    @Setter
    private String orderNo;
    private BigDecimal totalPrice;
    private StoreDto store;
    private List<ItemDto> items;

    public static OrderDto of(OrderEntity order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderNo(order.getOrderNo())
                .totalPrice(order.getTotalPrice())
                .store(StoreDto.of(order.getStore()))
                .build();
    }
}
