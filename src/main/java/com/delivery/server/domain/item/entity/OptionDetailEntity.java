package com.delivery.server.domain.item.entity;

import com.delivery.server.domain.order.entity.OrderOptionDetailEntity;
import com.delivery.server.domain.order.entity.OrderOptionEntity;
import com.delivery.server.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "options_detail")
public class OptionDetailEntity extends BaseEntity {
    @Comment("옵션 상세 이름")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Comment("옵션 상세 가격")
    @Column(name = "detail_price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal detailPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "options_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private OptionEntity option;

    /**
     * 주문 옵션 상세 생성 메서드
     * @param orderOption 주문 옵션
     * @return 주문 옵션 상세
     */
    public OrderOptionDetailEntity create(OrderOptionEntity orderOption) {
        return OrderOptionDetailEntity.builder()
                .orderOption(orderOption)
                .name(this.name)
                .detailPrice(this.detailPrice)
                .build();
    }

    /**
     * 검증 메서드
     * @param optionDetailName 옵션 상세 이름
     */
    public void verifyName(String optionDetailName) {
        if (!this.name.equals(optionDetailName)) {
            throw new IllegalArgumentException("주문하려는 옵션 상세가 아닙니다.");
        }
    }

    /**
     * 검증 메서드
     * @param detailPrice 옵션 상세 가격
     * @return 옵션 상세 가격
     */
    public BigDecimal verifyDetailPrice(BigDecimal detailPrice) {
        if (this.detailPrice.compareTo(detailPrice) != 0) {
            throw new IllegalArgumentException("옵션 상세 가격이 일치하지 않습니다.");
        }

        return this.detailPrice;
    }
}
