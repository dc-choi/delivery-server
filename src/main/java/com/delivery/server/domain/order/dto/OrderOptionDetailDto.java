package com.delivery.server.domain.order.dto;

import com.delivery.server.domain.order.entity.OrderOptionDetailEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderOptionDetailDto {
    private String name;
    private BigDecimal price;

    public static List<OrderOptionDetailDto> of(List<OrderOptionDetailEntity> orderOptionDetails) {
        return orderOptionDetails.stream()
                .map(OrderOptionDetailDto::of)
                .toList();
    }

    public static OrderOptionDetailDto of(OrderOptionDetailEntity orderOptionDetail) {
        return OrderOptionDetailDto.builder()
                .name(orderOptionDetail.getName())
                .price(orderOptionDetail.getDetailPrice())
                .build();
    }
}
