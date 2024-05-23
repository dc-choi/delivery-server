package com.delivery.server.domain.order.dto;

import com.delivery.server.domain.item.dto.ItemDto;
import com.delivery.server.domain.store.dto.StoreDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderDto {
    @Setter
    private String orderNo;
    private BigDecimal totalPrice;
    private StoreDto store;
    private List<ItemDto> items;
}
