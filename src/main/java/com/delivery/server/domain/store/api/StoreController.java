package com.delivery.server.domain.store.api;

import com.delivery.server.domain.order.dto.OrderDto;
import com.delivery.server.domain.store.application.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.delivery.server.domain.store.api.StoreController.STORE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(STORE_URL)
public class StoreController {
    public static final String STORE_URL = "/v1/stores";
    private final StoreService storeService;

    @GetMapping("{id}/orders")
    public List<OrderDto> getStoreOrders(@PathVariable("id") Long id) {
        return storeService.getStoreOrders(id);
    }
}
