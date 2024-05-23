package com.delivery.server.domain.item.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OptionDto {
    private Long id;
    private String name;
    private Boolean isRequired;
    private List<OptionDetailDto> optionDetails;
}
