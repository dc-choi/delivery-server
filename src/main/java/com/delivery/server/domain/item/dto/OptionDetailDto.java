package com.delivery.server.domain.item.dto;

import com.delivery.server.domain.item.entity.OptionDetailEntity;
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

    public OptionDetailEntity toEntity() {
        return OptionDetailEntity.builder()
            .id(this.id)
            .name(this.name)
            .detailPrice(this.price)
            .build();
    }
}
