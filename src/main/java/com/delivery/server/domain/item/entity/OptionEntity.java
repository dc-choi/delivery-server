package com.delivery.server.domain.item.entity;

import com.delivery.server.domain.order.entity.OrderDetailEntity;
import com.delivery.server.domain.order.entity.OrderOptionEntity;
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
@Table(name = "options")
public class OptionEntity extends BaseEntity {
    @Comment("옵션 이름")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Comment("옵션 필수 여부")
    @Column(name = "is_required", columnDefinition = "TINYINT UNSIGNED default 0")
    private Boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "items_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ItemEntity item;

    @OneToMany(mappedBy = "option")
    @Builder.Default
    private List<OptionDetailEntity> optionDetails = new ArrayList<>();

    /**
     * 주문 옵션 생성 메서드
     * @param orderDetail 주문 내역
     * @return 주문 옵션
     */
    public OrderOptionEntity create(OrderDetailEntity orderDetail) {
        return OrderOptionEntity.builder()
                .ordersDetail(orderDetail)
                .name(this.name)
                .build();
    }

    /**
     * 검증 메서드
     * @param optionName 옵션 이름
     */
    public void verifyName(String optionName) {
        if (!this.name.equals(optionName)) {
            throw new IllegalArgumentException("주문하려는 옵션이 아닙니다.");
        }
    }

    /**
     * 검증 메서드
     * @param optionDetails 옵션 상세 리스트
     */
    public void isRequired(List<OptionDetailEntity> optionDetails) {
        if (this.isRequired && optionDetails.isEmpty()) {
            throw new IllegalArgumentException("필수 옵션을 선택해주세요.");
        }
    }
}
