package com.delivery.server.domain.item.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private Long quantity;
    private BigDecimal unitPrice;
    private List<OptionDto> options;
}
