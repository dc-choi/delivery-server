package com.delivery.server.global.init;

import com.delivery.server.domain.order.dao.OrderDetailRepository;
import com.delivery.server.domain.order.dao.OrderOptionDetailRepository;
import com.delivery.server.domain.order.dao.OrderOptionRepository;
import com.delivery.server.domain.order.dao.OrderRepository;
import com.delivery.server.domain.order.entity.OrderDetailEntity;
import com.delivery.server.domain.order.entity.OrderEntity;
import com.delivery.server.domain.order.entity.OrderOptionDetailEntity;
import com.delivery.server.domain.order.entity.OrderOptionEntity;
import com.delivery.server.domain.store.dao.StoreRepository;
import com.delivery.server.domain.store.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Configuration
public class NotProd2 {
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderOptionRepository orderOptionRepository;
    private final OrderOptionDetailRepository orderOptionDetailRepository;

    @Bean
    @Order(2)
    public ApplicationRunner init2() {
        return args -> {
            System.out.println("init2");
//            StoreEntity bhc = storeRepository.findById(1L)
//                    .orElseThrow(() -> new IllegalArgumentException("삭제된 가게 입니다."));
//
//            for (int i = 0; i < 1000000; i++) {
//                OrderEntity order = OrderEntity.create(bhc);
//                orderRepository.save(order);
//
//                OrderDetailEntity orderDetail = OrderDetailEntity.builder()
//                        .order(order)
//                        .name("뿌링클")
//                        .quantity(2L)
//                        .unitPrice(BigDecimal.valueOf(19000))
//                        .build();
//                orderDetailRepository.save(orderDetail);
//
//                OrderOptionEntity orderOption = OrderOptionEntity.builder()
//                        .ordersDetail(orderDetail)
//                        .name("전용 소스")
//                        .build();
//                orderOptionRepository.save(orderOption);
//
//                OrderOptionDetailEntity orderOptionDetail = OrderOptionDetailEntity.builder()
//                        .orderOption(orderOption)
//                        .name("배송 여부")
//                        .detailPrice(BigDecimal.valueOf(0))
//                        .build();
//                orderOptionDetailRepository.save(orderOptionDetail);
        };
    }
}
