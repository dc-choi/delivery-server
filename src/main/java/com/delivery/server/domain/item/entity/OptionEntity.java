package com.delivery.server.domain.item.entity;

import com.delivery.server.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

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

    @Comment("필수 여부")
    @Column(name = "is_required", columnDefinition = "TINYINT UNSIGNED default 0")
    private Boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private ItemEntity item;
}
