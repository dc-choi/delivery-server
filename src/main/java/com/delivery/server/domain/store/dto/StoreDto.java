package com.delivery.server.domain.store.dto;

import com.delivery.server.domain.store.entity.StoreEntity;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class StoreDto {
    private Long id;
    private String name;

    public static StoreDto of(StoreEntity store) {
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .build();
    }
}
