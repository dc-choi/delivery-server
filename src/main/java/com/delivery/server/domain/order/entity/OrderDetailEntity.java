package com.delivery.server.domain.order.entity;

import com.delivery.server.domain.item.entity.ItemEntity;
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
@Table(name = "orders_detail")
public class OrderDetailEntity extends BaseEntity {
    @Comment("주문 상품명")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Comment("주문 상품 가격")
    @Column(name = "unit_price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal unitPrice;

    @Comment("주문 상품 수량")
    @Column(name = "quantity", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private OrderEntity order;

    @OneToMany(mappedBy = "ordersDetail")
    @Builder.Default
    private List<OrderOptionEntity> orderOptions = new ArrayList<>();
}
