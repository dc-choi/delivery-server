package com.delivery.server.domain.order.api;

import com.delivery.server.domain.order.application.OrderService;
import com.delivery.server.domain.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.delivery.server.global.common.url.Url.ORDER_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(ORDER_URL)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> order(@RequestBody OrderDto requestOrder) {
        OrderDto order = orderService.order(requestOrder);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrderNo())
                .toUri();

        return ResponseEntity.created(uri).body(order);
    }
}
