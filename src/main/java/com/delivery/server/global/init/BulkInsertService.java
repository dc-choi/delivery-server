package com.delivery.server.global.init;

import com.delivery.server.domain.order.dao.OrderDetailRepository;
import com.delivery.server.domain.order.dao.OrderOptionDetailRepository;
import com.delivery.server.domain.order.dao.OrderOptionRepository;
import com.delivery.server.domain.order.dao.OrderRepository;
import com.delivery.server.domain.order.entity.OrderDetailEntity;
import com.delivery.server.domain.order.entity.OrderEntity;
import com.delivery.server.domain.order.entity.OrderOptionDetailEntity;
import com.delivery.server.domain.order.entity.OrderOptionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkInsertService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderOptionRepository orderOptionRepository;
    private final OrderOptionDetailRepository orderOptionDetailRepository;

    @Transactional
    public void insert(
            List<OrderEntity> orders,
            List<OrderDetailEntity> orderDetails,
            List<OrderOptionEntity> orderOptions,
            List<OrderOptionDetailEntity> orderOptionDetails
    ) {
        orderRepository.saveAll(orders);
        orderDetailRepository.saveAll(orderDetails);
        orderOptionRepository.saveAll(orderOptions);
        orderOptionDetailRepository.saveAll(orderOptionDetails);
    }
}
