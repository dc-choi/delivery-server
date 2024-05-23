package com.delivery.server.domain.item.entity;

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
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal unitPrice;

    @Comment("판매 여부")
    @Column(name = "status", columnDefinition = "TINYINT UNSIGNED default 0")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private StoreEntity store;

    @OneToMany(mappedBy = "item")
    @Builder.Default
    private List<OptionEntity> options = new ArrayList<>();
}
