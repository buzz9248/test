package com.example.test_back.service.implementations;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.restaurant.PostRestaurantRequestDto;
import com.example.test_back.dto.restaurant.PostRestaurantResponseDto;
import com.example.test_back.dto.restaurant.RestaurantResponseDto;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public ResponseDto<PostRestaurantResponseDto> createRestaurant(PostRestaurantRequestDto dto) {
        PostRestaurantResponseDto respDto = null;

        Restaurant newRestaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        respDto = PostRestaurantResponseDto.builder()
                .id(savedRestaurant.getId())
                .name(savedRestaurant.getName())
                .address(savedRestaurant.getAddress())
                .phoneNumber(savedRestaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<RestaurantResponseDto> getRestaurantById(Long id) {
        RestaurantResponseDto respDto = null;

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + id));

        respDto = RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<RestaurantResponseDto>> getAllRestaurants() {
        List<RestaurantResponseDto> respDtos = null;

        List<Restaurant> restaurants = restaurantRepository.findAll();

        respDtos = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDtos);
    }

    @Override
    @Transactional
    public ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, PostRestaurantRequestDto dto) {
        RestaurantResponseDto respDto = null;

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + id));

        if(dto.getName() != null) {
            restaurant.setName(dto.getName());
        }
        if(dto.getAddress() != null) {
            restaurant.setAddress(dto.getAddress());
        }
        if(dto.getPhoneNumber() != null) {
            restaurant.setPhoneNumber(dto.getPhoneNumber());
        }

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        respDto = RestaurantResponseDto.builder()
                .id(updatedRestaurant.getId())
                .name(updatedRestaurant.getName())
                .address(updatedRestaurant.getAddress())
                .phoneNumber(updatedRestaurant.getPhoneNumber())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + id));

        restaurantRepository.deleteById(restaurant.getId());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
