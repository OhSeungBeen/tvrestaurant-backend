package kr.tvrestaurant.restaurant.dto;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import kr.tvrestaurant.restaurant.domain.Restaurant;
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

//    public Restaurant toEntity() {
//
//        Restaurant.builder()
//            .name(name)
//            .address(address)
//            .tel(tel)
//            .location(location)
//            .menus(menus.stream().map(menuDto -> menuDto.toEntity()).collect(Collectors.toList()))
//            .types(types.stream().map(typeDto -> typeDto.toEntity()).collect(Collectors.toList()))
//            .restaurantCategories()
//            .build();
//    }

}
