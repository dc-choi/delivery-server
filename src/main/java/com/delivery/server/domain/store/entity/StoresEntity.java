package com.delivery.server.domain.store.entity;

import com.delivery.server.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stores")
public class StoresEntity extends BaseEntity {
    @Comment("가게 상호명")
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;
}
