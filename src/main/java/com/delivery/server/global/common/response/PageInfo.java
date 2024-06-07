package com.delivery.server.global.common.response;

import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PageInfo {
    private int page;
    private int size;
    private Long total;

    public static PageInfo of(Page page) {
        return PageInfo.builder()
                .size(page.getPageable().getPageSize())
                .page(page.getPageable().getPageNumber() + 1)
                .total(page.getTotalElements())
                .build();
    }
}