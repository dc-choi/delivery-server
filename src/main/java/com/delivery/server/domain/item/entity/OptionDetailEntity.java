package com.delivery.server.domain.item.entity;

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
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private OptionEntity option;
}
