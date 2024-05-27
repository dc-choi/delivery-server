package com.delivery.server.domain.store.entity;

import com.delivery.server.domain.order.entity.OrderEntity;
import com.delivery.server.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "stores")
public class StoreEntity extends BaseEntity {
    @Comment("가게 상호명")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    public boolean verifyName(String name) {
        if (!this.name.equals(name)) {
            throw new IllegalArgumentException("가게 상호명이 일치하지 않습니다.");
        }

        return true;
    }
}
