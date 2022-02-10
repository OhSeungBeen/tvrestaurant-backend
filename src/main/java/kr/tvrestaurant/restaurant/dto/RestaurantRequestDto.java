package kr.tvrestaurant.restaurant.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import kr.tvrestaurant.restaurant.domain.Category;
import kr.tvrestaurant.restaurant.domain.Menu;
import kr.tvrestaurant.restaurant.domain.Restaurant;
import kr.tvrestaurant.restaurant.domain.RestaurantCategory;
import kr.tvrestaurant.restaurant.domain.Type;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.CategoryDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.geo.Point;

@Getter
@ToString
public class RestaurantRequestDto {

    @NotBlank(message = "이름을 작성하세요.")
    private String name;

    @NotBlank(message = "주소를 작성하세요.")
    private String address;
    
    @NotBlank(message = "전화번호를 작성하세요.")
    private String tel;

    @NotNull(message = "위치를 작성하세요.")
    private Point location;

    private List<RestaurantDto.MenuDto> menus;

    @NotNull(message = "타입을 작성하세요.")
    private List<RestaurantDto.TypeDto> types;

    @NotNull(message = "카테고리를 작성하세요.")
    private List<CategoryDto> categories;

    public Restaurant toEntity() {

        Restaurant restaurant = Restaurant.builder()
            .name(name)
            .address(address)
            .tel(tel)
            .location(location)
            .menus(new ArrayList<>())
            .types(new ArrayList<>())
            .restaurantCategories(new ArrayList<>())
            .build();

        List<Type> typesEntity = types.stream().map(typeDto -> typeDto.toEntity()).collect(Collectors.toList());
        List<Menu> menusEntity = menus.stream().map(menuDto -> menuDto.toEntity()).collect(Collectors.toList());

        List <RestaurantCategory> restaurantCategoriesEntity = new ArrayList<>();
        for(CategoryDto categoryDto : categories) {
            RestaurantCategory restaurantCategory = new RestaurantCategory();
            Category category = categoryDto.toEntity();
            category.addRestaurantCategory(restaurantCategory);
            restaurantCategoriesEntity.add(restaurantCategory);
        }

        restaurant.setMenus(menusEntity);
        restaurant.setTypes(typesEntity);
        restaurant.setRestaurantCategories(restaurantCategoriesEntity);

        return restaurant;
    }

}
