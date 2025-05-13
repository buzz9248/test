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

    // 메뉴를 특정 레스토랑에 생성
    @PostMapping
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> createMenu(@PathVariable Long restaurantId, @RequestBody PostMenuRequestDto dto) {
        ResponseDto<PostMenuResponseDto> response = menuService.createMenu(restaurantId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 특정 레스토랑에 속한 모든 메뉴 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getAllMenusByRestaurantId(@PathVariable Long restaurantId) {
        ResponseDto<List<MenuResponseDto>> response = menuService.getAllMenusByRestaurantId(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 특정 메뉴 정보를 ID로 조회
    @GetMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> getMenuById(@PathVariable Long restaurantId, @PathVariable Long menuId) {
        ResponseDto<MenuResponseDto> response = menuService.getMenuById(restaurantId, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 메뉴 정보 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> updateMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody PostMenuRequestDto dto) {
        ResponseDto<PostMenuResponseDto> response = menuService.updateMenu(restaurantId, menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 메뉴 개별 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(@PathVariable Long restaurantId, @PathVariable Long menuId) {
        ResponseDto<Void> response = menuService.deleteMenu(restaurantId, menuId);
        return ResponseEntity.noContent().build();
    }




}
