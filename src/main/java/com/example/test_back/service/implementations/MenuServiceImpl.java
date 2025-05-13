package com.example.test_back.service.implementations;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.ResponseDto;
import com.example.test_back.dto.menu.MenuResponseDto;
import com.example.test_back.dto.menu.PostMenuRequestDto;
import com.example.test_back.dto.menu.PostMenuResponseDto;
import com.example.test_back.dto.restaurant.RestaurantResponseDto;
import com.example.test_back.entity.Menu;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.MenuRepository;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public ResponseDto<PostMenuResponseDto> createMenu(Long restaurantId, PostMenuRequestDto dto) {
        PostMenuResponseDto respDto = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        Menu newMenu = Menu.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .restaurant(restaurant)
                .build();

        restaurant.addMenu(newMenu);

        Menu savedMenu = menuRepository.save(newMenu);


        respDto = new PostMenuResponseDto.Builder(savedMenu.getName(), savedMenu.getPrice(), savedMenu.getDescription())
                .id(savedMenu.getId())
                .restaurant(new RestaurantResponseDto(restaurantId, restaurant.getName(), restaurant.getAddress(), restaurant.getPhoneNumber()))
                .build();
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<MenuResponseDto>> getAllMenusByRestaurantId(Long restaurantId) {
        List<MenuResponseDto> respDto = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        respDto = restaurant.getMenus().stream()
                .map(menu -> MenuResponseDto.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .description(menu.getDescription())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<MenuResponseDto> getMenuById(Long restaurantId, Long menuId) {
        MenuResponseDto respDto = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_MENU + menuId));

        respDto = MenuResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    @Transactional
    public ResponseDto<PostMenuResponseDto> updateMenu(Long restaurantId, Long menuId, PostMenuRequestDto dto) {
        PostMenuResponseDto respDto = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_MENU + menuId));

        if (dto.getName() != null) {
            menu.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            menu.setDescription(dto.getDescription());
        }

        if (dto.getPrice() != null) {
            menu.setPrice(dto.getPrice());
        }

        Menu updatedMenu = menuRepository.save(menu);

        respDto = PostMenuResponseDto.builder()
                .name(updatedMenu.getName())
                .price(updatedMenu.getPrice())
                .description(updatedMenu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, respDto);
    }

    @Override
    public ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_MENU + menuId));

        menuRepository.deleteById(menuId);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
