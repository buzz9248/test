package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.restaurant.PostRestaurantRequestDto;
import com.example.test_back.dto.restaurant.PostRestaurantResponseDto;
import com.example.test_back.dto.restaurant.RestaurantResponseDto;
import com.example.test_back.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.RESTAURANT_API)
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 새로운 레스토랑 추가
    @PostMapping
    public ResponseEntity<ResponseDto<PostRestaurantResponseDto>> createRestaurant(@RequestBody PostRestaurantRequestDto dto) {
        ResponseDto<PostRestaurantResponseDto> response = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 레스토랑 ID로 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> getRestaurantById(@PathVariable Long id) {
        ResponseDto<RestaurantResponseDto> response = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 레스토랑 전체 조회 (페이징 기능 추가)
    @GetMapping
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> getAllRestaurants() {
        ResponseDto<List<RestaurantResponseDto>> response = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 레스토랑 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> updateRestaurant(@PathVariable Long id, @RequestBody PostRestaurantRequestDto dto) {
        ResponseDto<RestaurantResponseDto> response = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 레스토랑 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@PathVariable Long id) {
        ResponseDto<Void> response = restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }


}
