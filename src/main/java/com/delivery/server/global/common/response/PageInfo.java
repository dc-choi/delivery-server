package com.delivery.server.global.common.response;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PageInfo {
    private int page;
    private int size;
    private Long total;
    private boolean isLast;

    public static PageInfo of(Page page) {
        return PageInfo.builder()
                .size(page.getPageable().getPageSize())
                .page(page.getPageable().getPageNumber() + 1)
                .total(page.getTotalElements())
                .build();
    }

    public static PageInfo of(Slice slice) {
        return PageInfo.builder()
                .size(slice.getPageable().getPageSize())
                .page(slice.getPageable().getPageNumber())
                .isLast(slice.isLast())
                .build();
    }

    public static <T> Slice<T> checkLastPage(Pageable pageable, List<T> results) {
        boolean isLast = true;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음.
        if (results.size() > pageable.getPageSize()) {
            isLast = false;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, isLast);
    }
}