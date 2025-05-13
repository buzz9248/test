package com.example.test_back.service;

import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.restaurant.PostRestaurantRequestDto;
import com.example.test_back.dto.restaurant.PostRestaurantResponseDto;
import com.example.test_back.dto.restaurant.RestaurantResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    ResponseDto<PostRestaurantResponseDto> createRestaurant(PostRestaurantRequestDto dto);

    ResponseDto<RestaurantResponseDto> getRestaurantById(Long id);

    ResponseDto<List<RestaurantResponseDto>> getAllRestaurants();

    ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, PostRestaurantRequestDto dto);

    ResponseDto<Void> deleteRestaurant(Long id);
}
