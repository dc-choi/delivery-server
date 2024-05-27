package com.delivery.server.domain.order.entity;

import com.delivery.server.domain.item.dto.OptionDetailDto;
import com.delivery.server.domain.item.entity.OptionDetailEntity;
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
@Table(name = "orders_option_detail")
public class OrderOptionDetailEntity extends BaseEntity {
    @Comment("옵션 상세 이름")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Comment("옵션 상세 가격")
    @Column(name = "detail_price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal detailPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_option_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private OrderOptionEntity orderOption;

    public static OrderOptionDetailEntity create(OrderOptionEntity orderOption, OptionDetailEntity requestOptionDetail) {
        return OrderOptionDetailEntity.builder()
                .orderOption(orderOption)
                .name(requestOptionDetail.getName())
                .detailPrice(requestOptionDetail.getDetailPrice())
                .build();
    }
}
