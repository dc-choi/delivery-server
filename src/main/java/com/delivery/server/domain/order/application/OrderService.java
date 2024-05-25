package com.delivery.server.domain.order.application;

import com.delivery.server.domain.item.dao.ItemRepository;
import com.delivery.server.domain.item.dao.OptionDetailRepository;
import com.delivery.server.domain.item.dao.OptionRepository;
import com.delivery.server.domain.item.dto.ItemDto;
import com.delivery.server.domain.item.dto.OptionDetailDto;
import com.delivery.server.domain.item.dto.OptionDto;
import com.delivery.server.domain.item.entity.ItemEntity;
import com.delivery.server.domain.item.entity.OptionDetailEntity;
import com.delivery.server.domain.item.entity.OptionEntity;
import com.delivery.server.domain.order.dao.OrderDetailRepository;
import com.delivery.server.domain.order.dao.OrderOptionDetailRepository;
import com.delivery.server.domain.order.dao.OrderOptionRepository;
import com.delivery.server.domain.order.dao.OrderRepository;
import com.delivery.server.domain.order.dto.OrderDto;
import com.delivery.server.domain.order.entity.OrderDetailEntity;
import com.delivery.server.domain.order.entity.OrderEntity;
import com.delivery.server.domain.order.entity.OrderOptionDetailEntity;
import com.delivery.server.domain.order.entity.OrderOptionEntity;
import com.delivery.server.domain.store.dao.StoreRepository;
import com.delivery.server.domain.store.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderOptionRepository orderOptionRepository;
    private final OrderOptionDetailRepository orderOptionDetailRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;
    private final OptionDetailRepository optionDetailRepository;

    @Transactional
    public OrderDto order(OrderDto requestOrder) {
        // 1. 주문에 있는 가게 정보로 가게를 찾고 가게 상호명을 검증한다.
        StoreEntity store = storeRepository.findById(requestOrder.getStore().getId())
                .orElseThrow(() -> new IllegalArgumentException("삭제된 가게 입니다."));

        if (!store.getName().equals(requestOrder.getStore().getName())) {
            throw new IllegalArgumentException("가게 상호명이 일치하지 않습니다.");
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ItemDto requestItem : requestOrder.getItems()) {
            ItemEntity findItem = itemRepository.findById(requestItem.getId())
                    .orElseThrow(() -> new IllegalArgumentException("삭제된 상품 입니다."));

            // 2. 주문하려는 상품이 가게에 있는 상품이 맞는지 확인한다.
            if (!findItem.getStore().getName().equals(store.getName())) {
                throw new IllegalArgumentException("가게에 있는 상품이 아닙니다.");
            }

            // 3. 주문에 있는 상품 정보로 상품을 찾고 주문하려는 상품이 맞는지 확인한다.
            if (!findItem.getName().equals(requestItem.getName())) {
                throw new IllegalArgumentException("주문하려는 상품이 아닙니다.");
            }

            // 4. 주문하려는 상품이 판매 중인 상품인지 확인한다.
            if (!findItem.getStatus()) {
                throw new IllegalArgumentException("판매 중인 상품이 아닙니다.");
            }

            // 5. 주문에 있는 상품 정보로 가격과 수량을 곱하여 가격을 검증한다.
            BigDecimal quantity = BigDecimal.valueOf(requestItem.getQuantity());

            BigDecimal requestItemPrice = requestItem.getUnitPrice().multiply(quantity);
            BigDecimal itemPrice = findItem.getUnitPrice().multiply(quantity);

            if (requestItemPrice.compareTo(itemPrice) != 0) {
                throw new IllegalArgumentException("상품 가격이 일치하지 않습니다.");
            }

            totalPrice = totalPrice.add(requestItemPrice);

            // 6. 주문에 있는 상품의 옵션 정보로 옵션을 찾고 이름과 필요 여부에 따라 검증한다.
            List<OptionEntity> options = findItem.getOptions();

            for (OptionDto requestOption : requestItem.getOptions()) {
                OptionEntity findOption = optionRepository.findById(requestOption.getId())
                        .orElseThrow(() -> new IllegalArgumentException("삭제된 옵션 입니다."));

                if (!findOption.getName().equals(requestOption.getName())) {
                    throw new IllegalArgumentException("주문하려는 옵션이 아닙니다.");
                }

                for (OptionEntity requireOption : options) {
                    if (requireOption.getIsRequired() && requestOption.getOptionDetails().isEmpty()) {
                        throw new IllegalArgumentException("필수 옵션을 선택해주세요.");
                    }
                }

                // 7. 주문에 있는 상품의 옵션 상세 정보로 옵션 상세를 찾고 이름과 가격을 검증한다.
                for (OptionDetailDto requestOptionDetail : requestOption.getOptionDetails()) {
                    OptionDetailEntity findOptionDetail = optionDetailRepository.findById(requestOptionDetail.getId())
                            .orElseThrow(() -> new IllegalArgumentException("삭제된 옵션 상세 입니다."));

                    if (!findOptionDetail.getName().equals(requestOptionDetail.getName())) {
                        throw new IllegalArgumentException("주문하려는 옵션 상세가 아닙니다.");
                    }

                    if (findOptionDetail.getDetailPrice().compareTo(requestOptionDetail.getPrice()) != 0) {
                        throw new IllegalArgumentException("옵션 상세 가격이 일치하지 않습니다.");
                    }

                    totalPrice = totalPrice.add(requestOptionDetail.getPrice());
                }
            }
        }

        // 8. 주문의 총 가격을 계산하여 검증한다.
        if (totalPrice.compareTo(requestOrder.getTotalPrice()) != 0) {
            throw new IllegalArgumentException("주문 총 가격이 일치하지 않습니다.");
        }

        // 9. 주문을 저장한다.
        OrderEntity order = OrderEntity.builder()
                .orderNo(UUID.randomUUID().toString())
                .store(store)
                .totalPrice(totalPrice)
                .build();
        requestOrder.setOrderNo(order.getOrderNo());

        for (ItemDto requestItem : requestOrder.getItems()) {
            OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                    .order(order)
                    .name(requestItem.getName())
                    .quantity(requestItem.getQuantity())
                    .unitPrice(requestItem.getUnitPrice())
                    .build();

            orderDetailRepository.save(orderDetail);

            for (OptionDto requestOption : requestItem.getOptions()) {
                OrderOptionEntity orderOption = OrderOptionEntity.builder()
                        .ordersDetail(orderDetail)
                        .name(requestOption.getName())
                        .build();

                orderOptionRepository.save(orderOption);

                for (OptionDetailDto requestOptionDetail : requestOption.getOptionDetails()) {
                    OrderOptionDetailEntity orderOptionDetail = OrderOptionDetailEntity.builder()
                            .orderOption(orderOption)
                            .name(requestOptionDetail.getName())
                            .detailPrice(requestOptionDetail.getPrice())
                            .build();

                    orderOptionDetailRepository.save(orderOptionDetail);
                }
            }
        }

        orderRepository.save(order);

        return requestOrder;
    }
}
