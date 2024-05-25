package com.delivery.server.domain.order.dto;

import com.delivery.server.domain.order.entity.OrderOptionEntity;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderOptionDto {
    private String name;
    private List<OrderOptionDetailDto> orderOptionDetails;

    public static List<OrderOptionDto> of(List<OrderOptionEntity> orderOptions) {
        return orderOptions.stream()
                .map(OrderOptionDto::of)
                .toList();
    }

    public static OrderOptionDto of(OrderOptionEntity orderOption) {
        return OrderOptionDto.builder()
                .name(orderOption.getName())
                .orderOptionDetails(OrderOptionDetailDto.of(orderOption.getOrderOptionDetails()))
                .build();
    }
}
