package com.delivery.server.global.init;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class NotProd2 {
    private final StoreRepository storeRepository;
    private final BulkInsertService bulkInsertService;

    @Bean
    @Order(2)
    public ApplicationRunner init2() {
        return args -> {
            System.out.println("init2");
//            StoreEntity bhc = storeRepository.findById(1L)
//                    .orElseThrow(() -> new IllegalArgumentException("삭제된 가게 입니다."));
//
//            List<OrderEntity> orderEntities = new ArrayList<>();
//            List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
//            List<OrderOptionEntity> orderOptionEntities = new ArrayList<>();
//            List<OrderOptionDetailEntity> orderOptionDetailEntities = new ArrayList<>();
//
//            for (long i = 0; i < 10000000; i++) {
//                OrderEntity order = OrderEntity.create(bhc, LocalDateTime.now());
//                orderEntities.add(order);
//
//                OrderDetailEntity orderDetail = OrderDetailEntity.builder()
//                        .order(order)
//                        .name("뿌링클")
//                        .quantity(2L)
//                        .unitPrice(BigDecimal.valueOf(19000))
//                        .build();
//                orderDetailEntities.add(orderDetail);
//
//                OrderOptionEntity orderOption = OrderOptionEntity.builder()
//                        .ordersDetail(orderDetail)
//                        .name("전용 소스")
//                        .build();
//                orderOptionEntities.add(orderOption);
//
//                OrderOptionDetailEntity orderOptionDetail = OrderOptionDetailEntity.builder()
//                        .orderOption(orderOption)
//                        .name("배송 여부")
//                        .detailPrice(BigDecimal.valueOf(0))
//                        .build();
//                orderOptionDetailEntities.add(orderOptionDetail);
//
//                if (i % 1000 == 0) {
//                    bulkInsertService.insert(orderEntities, orderDetailEntities, orderOptionEntities, orderOptionDetailEntities);
//                    orderEntities.clear();
//                    orderDetailEntities.clear();
//                    orderOptionEntities.clear();
//                    orderOptionDetailEntities.clear();
//                }
//            }
//
//            bulkInsertService.insert(orderEntities, orderDetailEntities, orderOptionEntities, orderOptionDetailEntities);
        };
    }
}
