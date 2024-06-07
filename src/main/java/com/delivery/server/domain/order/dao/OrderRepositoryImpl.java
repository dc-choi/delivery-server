package com.delivery.server.domain.order.dao;

import com.delivery.server.domain.order.entity.OrderEntity;
import com.delivery.server.global.common.response.PageInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.delivery.server.domain.order.entity.QOrderEntity.orderEntity;
import static com.delivery.server.domain.store.entity.QStoreEntity.storeEntity;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<OrderEntity> findByStoreId(Long storeId, Pageable pageable, LocalDate start, LocalDate end) {
        BooleanExpression where = orderEntity.store.id.eq(storeId)
                .and(orderEntity.id.lt(pageable.getOffset()));

        where = start != null && end != null
                        ? where.and(orderEntity.createdAt.between(start.atStartOfDay(), end.atTime(23, 59, 59)))
                        : where;

        List<OrderEntity> orders = jpaQueryFactory
                .select(orderEntity)
                .from(orderEntity)
                .leftJoin(storeEntity).on(storeEntity.eq(orderEntity.store))
                .where(where)
                .orderBy(orderEntity.id.desc())
                .limit(pageable.getPageSize())
                .fetch();

//        JPAQuery<Long> total = jpaQueryFactory
//                .select(orderEntity.count())
//                .from(orderEntity)
//                .leftJoin(storeEntity).on(storeEntity.eq(orderEntity.store))
//                .where(where)
//                .orderBy(orderEntity.id.desc());

//        return PageableExecutionUtils.getPage(orders, pageable, total::fetchOne);
        return PageInfo.checkLastPage(pageable, orders);
    }
}
