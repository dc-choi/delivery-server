package com.delivery.server.domain.order.entity;

import com.delivery.server.domain.store.entity.StoreEntity;
import com.delivery.server.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @Comment("주문 번호")
    @Column(name = "order_no", nullable = false, columnDefinition = "VARCHAR(255)")
    private String orderNo;

    @Comment("주문 가격")
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private StoreEntity store;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();
}
