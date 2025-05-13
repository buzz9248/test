# test

# ERD 설계도
<img width="1680" alt="Image" src="https://github.com/user-attachments/assets/6f503c14-3830-4486-a02e-fdccce8132ab" />



# API 설계서 (요청 및 응답 예시 포함)

## restaurant

| 기능       | Method | URL                
|----------|--------|--------------------
| 레스토랑 생성    | POST   | /api/v1/restaurants     
| 레스토랑 전체 조회    | GET    | /api/v1/restaurants
| 레스토랑 ID로 조회 | GET    | /api/v1/restaurants/{id}     
| 레스토랑 정보 수정    | PUT    | /api/v1/restaurants/{id}  
| 레스토랑 삭제    | DELETE | /api/v1/restaurants/{id}     


## menu

| 기능       | Method | URL                
|----------|--------|--------------------
| 메뉴 생성    | POST   | /api/v1/restaurants/{restaurantId}/menus
| 특정 레스토랑 (ID로) 메뉴 전체 조회    | GET    | /api/v1/restaurants/{restaurantId}/menus
| 메뉴 ID로 조회 | GET    | /api/v1/restaurants/{restaurantId}/menus/{menuId}
| 메뉴 수정    | PUT    | /api/v1/restaurants/{restaurantId}/menus/{menuId}
| 메뉴 삭제    | DELETE | /api/v1/restaurants/{restaurantId}/menus/{menuId}
