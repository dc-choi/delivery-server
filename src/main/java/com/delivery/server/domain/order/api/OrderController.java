package com.delivery.server.domain.order.api;

import com.delivery.server.domain.order.application.OrderService;
import com.delivery.server.domain.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.delivery.server.domain.order.api.OrderController.ORDER_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(ORDER_URL)
public class OrderController {
    public static final String ORDER_URL = "/v1/orders";
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> order(@RequestBody OrderDto requestOrder) {
        return ResponseEntity.created(URI.create(ORDER_URL)).body(orderService.order(requestOrder));
    }
}
