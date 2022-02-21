package kr.tvrestaurant.restaurant.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.tvrestaurant.restaurant.application.domain.Menu;
import kr.tvrestaurant.restaurant.application.domain.Restaurant;
import kr.tvrestaurant.restaurant.application.domain.RestaurantCategory;
import kr.tvrestaurant.restaurant.application.domain.RestaurantRepository;
import kr.tvrestaurant.restaurant.application.domain.Type;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.CategoryDto;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.MenuDto;
import kr.tvrestaurant.restaurant.dto.RestaurantDto.TypeDto;
import kr.tvrestaurant.restaurant.dto.RestaurantRequestDto;
import kr.tvrestaurant.restaurant.dto.RestaurantResponseDto;
import kr.tvrestaurant.restaurant.exception.NoExistRestaurantException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantResponseDto addRestaurant(RestaurantRequestDto restaurantRequestDto) {

        return new RestaurantResponseDto(restaurantRepository.save(restaurantRequestDto.toEntity()));
    }

    public List<RestaurantResponseDto> getRestaurantList() {

        return restaurantRepository.findAll().stream()
            .map(RestaurantResponseDto::new)
            .collect(Collectors.toList());
    }

    public void getRestaurantListByLocation(Point southWest, Point eastNorth) {

    }

    public RestaurantResponseDto getRestaurantDetails(Long id) {
        Restaurant restaurant = existRestaurant(restaurantRepository.findById(id));

        return new RestaurantResponseDto(restaurant);
    }

    @Transactional
    public RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = existRestaurant(restaurantRepository.findById(id));
        restaurant.setName(restaurantRequestDto.getName());
        restaurant.setAddress(restaurantRequestDto.getAddress());
//        restaurant.setLocation(restaurantRequestDto.getLocation());
        restaurant.setTel(restaurantRequestDto.getTel());

        for(MenuDto menuDto: restaurantRequestDto.getMenus()) {
            Menu findMenu = restaurant.getMenus().stream()
                .filter(menu -> menu.getId().equals(menuDto.getId())).findFirst().get();
            findMenu.setName(menuDto.getName());
            findMenu.setPrice(menuDto.getPrice());
        }

        for(TypeDto typeDto: restaurantRequestDto.getTypes()) {
            Type findType = restaurant.getTypes().stream()
                .filter(type -> type.getId().equals(typeDto.getId())).findFirst().get();
            findType.setName(typeDto.getName());
            findType.setEpisode(typeDto.getEpisode());
        }

        for(CategoryDto categoryDto: restaurantRequestDto.getCategories()) {
            RestaurantCategory findRestaurantCategory = restaurant.getRestaurantCategories()
                .stream().filter(restaurantCategory ->
                    restaurantCategory.getCategory().getId().equals(categoryDto.getId())).findFirst().get();
            findRestaurantCategory.getCategory().setName(categoryDto.getName());
        }

        return new RestaurantResponseDto(restaurantRepository.save(restaurant));
    }

    @Transactional
    public RestaurantResponseDto removeRestaurant(Long id) {
        Restaurant restaurant = existRestaurant(restaurantRepository.findById(id));

        restaurantRepository.delete(restaurant);
        return new RestaurantResponseDto(restaurant);
    }

    private Restaurant existRestaurant(Optional<Restaurant> restaurantOpt) throws NoExistRestaurantException{
        restaurantOpt.orElseThrow(() -> new NoExistRestaurantException("존재하지 않는 식당입니다."));

        return restaurantOpt.get();
    }



}
