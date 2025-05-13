package com.example.test_back.service;

import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.menu.MenuResponseDto;
import com.example.test_back.dto.menu.PostMenuRequestDto;
import com.example.test_back.dto.menu.PostMenuResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    ResponseDto<PostMenuResponseDto> createMenu(Long restaurantId, PostMenuRequestDto dto);

    ResponseDto<List<MenuResponseDto>> getAllMenusByRestaurantId(Long restaurantId);

    ResponseDto<MenuResponseDto> getMenuById(Long restaurantId, Long menuId);

    ResponseDto<PostMenuResponseDto> updateMenu(Long restaurantId, Long menuId, PostMenuRequestDto dto);

    ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId);
}
