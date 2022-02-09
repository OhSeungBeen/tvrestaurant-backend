package kr.tvrestaurant.restaurant.dto;

import kr.tvrestaurant.restaurant.RestaurantCategory;
import kr.tvrestaurant.restaurant.domain.Menu;
import kr.tvrestaurant.restaurant.domain.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    }

    @Getter
    @NoArgsConstructor
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
