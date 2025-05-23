package com.example.test_back.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MenuResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
}
