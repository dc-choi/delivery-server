package com.delivery.server.domain.order.entity;

import com.delivery.server.domain.item.dto.OptionDetailDto;
import com.delivery.server.domain.item.dto.OptionDto;
import com.delivery.server.domain.item.entity.OptionEntity;
import com.delivery.server.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders_option")
public class OrderOptionEntity extends BaseEntity {
    @Comment("옵션 이름")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_detail_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private OrderDetailEntity ordersDetail;

    @OneToMany(mappedBy = "orderOption")
    @Builder.Default
    private List<OrderOptionDetailEntity> orderOptionDetails = new ArrayList<>();

    public static OrderOptionEntity create(OrderDetailEntity orderDetail, OptionEntity requestOption) {
        return OrderOptionEntity.builder()
                .ordersDetail(orderDetail)
                .name(requestOption.getName())
                .build();
    }
}
