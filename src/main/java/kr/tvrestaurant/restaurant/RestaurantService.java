package kr.tvrestaurant.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.tvrestaurant.restaurant.domain.Category;
import kr.tvrestaurant.restaurant.domain.Menu;
import kr.tvrestaurant.restaurant.domain.Restaurant;
import kr.tvrestaurant.restaurant.domain.Type;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.CategoryDto;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.MenuDto;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.TypeDto;
import kr.tvrestaurant.restaurant.dto.RestaurantRequestDto;
import kr.tvrestaurant.restaurant.dto.RestaurantResponseDto;
import kr.tvrestaurant.restaurant.exception.NoExistRestaurantException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantResponseDto addRestaurant(RestaurantRequestDto restaurantRequestDto) {

        Restaurant restaurant = Restaurant.builder()
            .name(restaurantRequestDto.getName())
            .address(restaurantRequestDto.getAddress())
            .tel(restaurantRequestDto.getTel())
            .location(restaurantRequestDto.getLocation())
            .menus(new ArrayList<>())
            .types(new ArrayList<>())
            .restaurantCategories(new ArrayList<>())
            .build();

        if(restaurantRequestDto.getMenus() != null){
            for (MenuDto menuDto : restaurantRequestDto.getMenus()) {
                Menu menu = Menu.builder()
                    .name(menuDto.getName())
                    .price(menuDto.getPrice())
                    .build();
                restaurant.addMenu(menu);
            }
        }

        for (TypeDto typeDto : restaurantRequestDto.getTypes()) {
            Type type = Type.builder()
                .name(typeDto.getName())
                .episode(typeDto.getEpisode())
                .build();
            restaurant.addType(type);
        }

        List<RestaurantCategory> restaurantCategories = new ArrayList<>();

        for (CategoryDto categoryDto : restaurantRequestDto.getCategories()) {
            RestaurantCategory restaurantCategory = new RestaurantCategory();
            Category category = Category.builder()
                .name(categoryDto.getName())
                .restaurantCategories(new ArrayList<>())
                .build();
            category.addRestaurantCategory(restaurantCategory);
            restaurantCategories.add(restaurantCategory);
        }

        for (RestaurantCategory restaurantCategory : restaurantCategories) {
            restaurant.addRestaurantCategory(restaurantCategory);
        }

        restaurantRepository.save(restaurant);

        return new RestaurantResponseDto(restaurant);
    }

    public List<RestaurantResponseDto> getRestaurantList() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants.stream()
            .map(r -> new RestaurantResponseDto(r))
            .collect(Collectors.toList());
    }

    public RestaurantResponseDto getRestaurantDetails(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new NoExistRestaurantException("존재하지 않는 식당입니다."));
        return new RestaurantResponseDto(restaurant);
    }

    @Transactional
    public RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(id)
            .orElseThrow(() -> new NoExistRestaurantException("존재하지 않는 식당입니다."));

        restaurant.setName(restaurantRequestDto.getName());
        restaurant.setAddress(restaurantRequestDto.getAddress());
        restaurant.setLocation(restaurantRequestDto.getLocation());
        restaurant.setTel(restaurantRequestDto.getTel());

//        for(Menu menu : restaurant.getMenus()) {
//            menu.setName();
//        }

//        restaurant.getRestaurantCategories().
//        restaurant.setMenus(restaurantRequestDto.getMenus());
//        restaurant.setTypes(restaurantRequestDto.getTypes());
//        restaurant.setRestaurantCategories(restaurantRequestDto.getCategories());
//        restaurantRepository.save(restaurant);

        return new RestaurantResponseDto(restaurant);
    }
}
