package kr.tvrestaurant.restaurant.dto;

import java.util.ArrayList;
import kr.tvrestaurant.restaurant.application.domain.Category;
import kr.tvrestaurant.restaurant.application.domain.RestaurantCategory;
import kr.tvrestaurant.restaurant.application.domain.Menu;
import kr.tvrestaurant.restaurant.application.domain.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class RestaurantDto {
    @Getter
    @NoArgsConstructor
    public static class CategoryDto {

        private Long id;
        private String name;

        public CategoryDto(RestaurantCategory restaurantCategory) {
            this.id = restaurantCategory.getCategory().getId();
            this.name = restaurantCategory.getCategory().getName();
        }

        public Category toEntity() {
            Category category = Category.builder()
                .name(name)
                .restaurantCategories(new ArrayList<>())
                .build();
            return category;
        }
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class MenuDto {

        private Long id;
        private String name;
        private int price;

        public MenuDto(Menu menu) {
            this.id = menu.getId();
            this.name = menu.getName();
            this.price = menu.getPrice();
        }

        public Menu toEntity() {
            return Menu.builder()
                .name(name)
                .price(price)
                .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class TypeDto {

        private Long id;
        private String name;
        private int episode;

        public TypeDto(Type type) {
            this.id = type.getId();
            this.name = type.getName();
            this.episode = type.getEpisode();
        }

        public Type toEntity() {
            return Type.builder()
                .name(name)
                .episode(episode)
                .build();
        }
    }
}
