package com.delivery.server.domain.item.entity;

import com.delivery.server.domain.order.entity.OrderDetailEntity;
import com.delivery.server.domain.order.entity.OrderEntity;
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
@Table(name = "items")
public class ItemEntity extends BaseEntity {
    @Comment("상품명")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Comment("상품 단가")
    @Column(name = "unit_price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal unitPrice;

    @Comment("판매 여부")
    @Column(name = "status", columnDefinition = "TINYINT UNSIGNED default 0")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stores_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private StoreEntity store;

    @OneToMany(mappedBy = "item")
    @Builder.Default
    private List<OptionEntity> options = new ArrayList<>();

    /**
     * 주문 내역 생성 메서드
     * @param order 주문
     * @param quantity 상품 수량
     * @return 주문 내역
     */
    public OrderDetailEntity create(OrderEntity order, Long quantity) {
        return OrderDetailEntity.builder()
                .order(order)
                .name(this.name)
                .quantity(quantity)
                .unitPrice(this.unitPrice)
                .build();
    }

    /**
     * 검증 메서드
     * @param storeName 가게 이름
     */
    public void isItemInStore(String storeName) {
        if (!this.store.verifyName(storeName)) {
            throw new IllegalArgumentException("가게에 있는 상품이 아닙니다.");
        }
    }

    /**
     * 검증 메서드
     * @param itemName 상품 이름
     */
    public void verifyName(String itemName) {
        if (!this.name.equals(itemName)) {
            throw new IllegalArgumentException("주문하려는 상품이 아닙니다.");
        }
    }

    /**
     * 검증 메서드
     */
    public void isItemSale() {
        if (!this.status) {
            throw new IllegalArgumentException("판매 중인 상품이 아닙니다.");
        }
    }

    /**
     * 검증 메서드
     * @param unitPrice 상품 단가
     * @param itemQuantity 상품 수량
     * @return 상품 가격
     */
    public BigDecimal verifyPrice(BigDecimal unitPrice, Long itemQuantity) {
        BigDecimal quantity = BigDecimal.valueOf(itemQuantity);

        BigDecimal requestItemPrice = unitPrice.multiply(quantity);
        BigDecimal itemPrice = this.unitPrice.multiply(quantity);

        if (requestItemPrice.compareTo(itemPrice) != 0) {
            throw new IllegalArgumentException("상품 가격이 일치하지 않습니다.");
        }

        return itemPrice;
    }
}
