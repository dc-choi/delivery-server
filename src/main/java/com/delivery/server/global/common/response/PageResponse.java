package com.delivery.server.global.common.response;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PageResponse<T> {
    private T data;
    private PageInfo pageInfo;

    /**
     * 여러 DTO를 data로 넣어주기 위해선 원시타입으로밖에 사용할 수 없음...
     */
    public static <T> PageResponse of(Slice<T> slice) {
        return PageResponse.builder()
                .data(slice.getContent())
                .pageInfo(PageInfo.of(slice))
                .build();
    }
}