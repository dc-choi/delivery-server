package com.delivery.server.domain.store.api;

import com.delivery.server.domain.order.dto.OrderDto;
import com.delivery.server.domain.store.application.StoreService;
import com.delivery.server.global.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.delivery.server.global.common.url.Url.STORE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(STORE_URL)
public class StoreController {
    private final StoreService storeService;

    @GetMapping("{id}/orders")
    public ResponseEntity<PageResponse<OrderDto>> getStoreOrders(
            @PathVariable("id") Long id,
            // INFO: 보통 page의 경우 1부터 시작하지만 Spring Data JPA는 0부터 시작함
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(name = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        if (page <= 0) page = 1;

        Pageable pageable = PageRequest.of(page - 1, size);

        return ResponseEntity.ok().body(storeService.getStoreOrders(id, pageable, start, end));
    }
}
