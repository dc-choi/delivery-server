package com.delivery.server.domain.order.application;

import com.delivery.server.domain.item.dao.ItemRepository;
import com.delivery.server.domain.item.dao.OptionDetailRepository;
import com.delivery.server.domain.item.dao.OptionRepository;
import com.delivery.server.domain.item.dto.OptionDetailDto;
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

        store.verifyName(requestOrder.getStore().getName());

        OrderEntity order = OrderEntity.create(store);
        requestOrder.setOrderNo(order.getOrderNo());

        requestOrder.getItems().forEach(requestItem -> {
            ItemEntity findItem = itemRepository.findById(requestItem.getId())
                    .orElseThrow(() -> new IllegalArgumentException("삭제된 상품 입니다."));

            // 2. 주문하려는 상품이 가게에 있는 상품이 맞는지 확인한다.
            findItem.isItemInStore(store.getName());

            // 3. 주문에 있는 상품 정보로 상품을 찾고 주문하려는 상품이 맞는지 확인한다.
            findItem.verifyName(requestItem.getName());

            // 4. 주문하려는 상품이 판매 중인 상품인지 확인한다.
            findItem.isItemSale();

            // 5. 주문에 있는 상품 정보로 가격과 수량을 곱하여 가격을 검증한다.
            order.addTotalPrice(findItem.verifyPrice(requestItem.getUnitPrice(), requestItem.getQuantity()));

            OrderDetailEntity orderDetail = findItem.create(order, requestItem.getQuantity());
            orderDetailRepository.save(orderDetail);

            // 6. 주문에 있는 상품의 옵션 정보로 옵션을 찾고 이름과 필요 여부에 따라 검증한다.
            requestItem.getOptions().forEach(requestOption -> {
                OptionEntity findOption = optionRepository.findById(requestOption.getId())
                        .orElseThrow(() -> new IllegalArgumentException("삭제된 옵션 입니다."));

                findOption.verifyName(requestOption.getName());

                findOption.isRequired(requestOption.getOptionDetails().stream()
                        .map(OptionDetailDto::toEntity)
                        .toList());

                OrderOptionEntity orderOption = findOption.create(orderDetail);
                orderOptionRepository.save(orderOption);

                // 7. 주문에 있는 상품의 옵션 상세 정보로 옵션 상세를 찾고 이름과 가격을 검증한다.
                requestOption.getOptionDetails().forEach(requestOptionDetail -> {
                    OptionDetailEntity findOptionDetail = optionDetailRepository.findById(requestOptionDetail.getId())
                            .orElseThrow(() -> new IllegalArgumentException("삭제된 옵션 상세 입니다."));

                    findOptionDetail.verifyName(requestOptionDetail.getName());

                    order.addTotalPrice(findOptionDetail.verifyDetailPrice(requestOptionDetail.getPrice()));

                    orderOptionDetailRepository.save(findOptionDetail.create(orderOption));
                });
            });
        });

        // 8. 주문의 총 가격을 계산하여 검증한다.
        order.verifyTotalPrice(requestOrder.getTotalPrice());

        // 9. 주문을 저장한다.
        orderRepository.save(order);

        return requestOrder;
    }
}
