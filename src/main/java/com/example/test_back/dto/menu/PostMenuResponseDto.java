package com.example.test_back.dto.menu;

import com.example.test_back.dto.restaurant.RestaurantResponseDto;
import com.example.test_back.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostMenuResponseDto {
    private Long id;
    private final String name;
    private final Double price;
    private final String description;

    private RestaurantResponseDto restaurant;

    public static class Builder {
        private Long id;
        private final String name;
        private final Double price;
        private final String description;
        private RestaurantResponseDto restaurant;

        public Builder(String name, Double price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder restaurant(RestaurantResponseDto restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public PostMenuResponseDto build() {
            return new PostMenuResponseDto(id, name, price, description, restaurant);
        }

    }


}
