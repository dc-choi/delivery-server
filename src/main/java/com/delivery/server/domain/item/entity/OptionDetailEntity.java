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
    @Column(name = "detail_price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal detailPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "options_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private OptionEntity option;

    public void verifyName(String name) {
        if (!this.name.equals(name)) {
            throw new IllegalArgumentException("주문하려는 옵션 상세가 아닙니다.");
        }
    }

    public BigDecimal verifyDetailPrice(BigDecimal price) {
        if (this.detailPrice.compareTo(price) != 0) {
            throw new IllegalArgumentException("옵션 상세 가격이 일치하지 않습니다.");
        }

        return this.detailPrice;
    }
}
