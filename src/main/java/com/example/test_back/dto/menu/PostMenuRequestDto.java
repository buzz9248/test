package com.example.test_back.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMenuRequestDto {
    private String name;
    private Double price;
    private String description;
    private Long restaurantId;
}
