package com.delivery.server.domain.store.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class StoreDto {
    private Long id;
    private String name;
}
