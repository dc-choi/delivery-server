package com.delivery.server.domain.order.dto;

import com.delivery.server.domain.order.entity.OrderDetailEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderDetailDto {
    private String name;
    private BigDecimal price;
    private Long quantity;
    private List<OrderOptionDto> orderOptions;

    public static List<OrderDetailDto> of(List<OrderDetailEntity> orderDetailEntities) {
        return orderDetailEntities.stream()
                .map(OrderDetailDto::of)
                .toList();
    }

    public static OrderDetailDto of(OrderDetailEntity orderDetailEntity) {
        return OrderDetailDto.builder()
                .name(orderDetailEntity.getName())
                .price(orderDetailEntity.getPrice())
                .quantity(orderDetailEntity.getQuantity())
                .orderOptions(OrderOptionDto.of(orderDetailEntity.getOrderOptions()))
                .build();
    }
}
