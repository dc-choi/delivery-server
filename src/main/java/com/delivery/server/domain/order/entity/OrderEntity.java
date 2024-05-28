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
import java.util.UUID;

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

    @Comment("주문 총 가격")
    @Column(name = "total_price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stores_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private StoreEntity store;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

    /**
     * 주문 생성 메서드
     * @param store 가게
     * @return 주문
     */
    public static OrderEntity create(StoreEntity store) {
        return OrderEntity.builder()
                .orderNo(generateOrderNo())
                .totalPrice(BigDecimal.ZERO)
                .store(store)
                .build();
    }

    /**
     * 주문 번호 생성 메서드
     * @return 주문 번호
     */
    private static String generateOrderNo() {
        return UUID.randomUUID().toString();
    }

    /**
     * 가격 추가 메서드
     * @param price 가격
     */
    public void addTotalPrice(BigDecimal price) {
        this.totalPrice = this.totalPrice.add(price);
    }

    /**
     * 검증 메서드
     * @param totalPrice 주문 총 가격
     */
    public void verifyTotalPrice(BigDecimal totalPrice) {
        if (this.totalPrice.compareTo(totalPrice) != 0) {
            throw new IllegalArgumentException("주문 총 가격이 일치하지 않습니다.");
        }
    }
}
