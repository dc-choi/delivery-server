package com.delivery.server.domain.item.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OptionDetailDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
