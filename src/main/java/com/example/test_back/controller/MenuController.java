package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.menu.MenuResponseDto;
import com.example.test_back.dto.menu.PostMenuRequestDto;
import com.example.test_back.dto.menu.PostMenuResponseDto;
import com.example.test_back.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU_API)
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> createMenu(@PathVariable Long restaurantId, @RequestBody PostMenuRequestDto dto) {
        ResponseDto<PostMenuResponseDto> response = menuService.createMenu(restaurantId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getAllMenusByRestaurantId(@PathVariable Long restaurantId) {
        ResponseDto<List<MenuResponseDto>> response = menuService.getAllMenusByRestaurantId(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> getMenuById(@PathVariable Long restaurantId, @PathVariable Long menuId) {
        ResponseDto<MenuResponseDto> response = menuService.getMenuById(restaurantId, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> updateMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody PostMenuRequestDto dto) {
        ResponseDto<PostMenuResponseDto> response = menuService.updateMenu(restaurantId, menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(@PathVariable Long restaurantId, @PathVariable Long menuId) {
        ResponseDto<Void> response = menuService.deleteMenu(restaurantId, menuId);
        return ResponseEntity.noContent().build();
    }




}
