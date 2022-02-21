package kr.tvrestaurant.restaurant.dto;

import java.util.List;
import java.util.stream.Collectors;
import kr.tvrestaurant.restaurant.application.domain.Restaurant;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private String address;
    private String tel;
    private double latitude;
    private double longitude;
    private List<RestaurantDto.MenuDto> menus;
    private List<RestaurantDto.TypeDto> types;
    private List<CategoryDto> categories;

    public RestaurantResponseDto(Restaurant restaurant) {

        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.tel = restaurant.getTel();
        this.latitude = restaurant.getLocation().getCoordinate().getX();
        this.longitude = restaurant.getLocation().getCoordinate().getY();
        this.menus = restaurant.getMenus()
            .stream().map(menu -> new RestaurantDto.MenuDto(menu))
            .collect(Collectors.toList());
        this.types = restaurant.getTypes()
            .stream().map(type -> new RestaurantDto.TypeDto(type))
            .collect(Collectors.toList());
        this.categories = restaurant.getRestaurantCategories()
            .stream().map(restaurantCategory -> new CategoryDto(restaurantCategory))
            .collect(Collectors.toList());
    }
}


