package com.delivery.server.domain.item.entity;

import com.delivery.server.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void verifyName(String name) {
        if (!this.name.equals(name)) {
            throw new IllegalArgumentException("주문하려는 옵션이 아닙니다.");
        }
    }

    public void isRequired(List<OptionDetailEntity> optionDetails) {
        if (this.isRequired && optionDetails.isEmpty()) {
            throw new IllegalArgumentException("필수 옵션을 선택해주세요.");
        }
    }
}
