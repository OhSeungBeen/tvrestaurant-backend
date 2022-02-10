package kr.tvrestaurant.restaurant.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<RestaurantCategory> restaurantCategories = new ArrayList<>();

    public void setRestaurantCategories(List<RestaurantCategory> restaurantCategories) {
        restaurantCategories.forEach(restaurantCategory -> restaurantCategory.setCategory(this));
        this.restaurantCategories = restaurantCategories;
    }

    public void addRestaurantCategory(RestaurantCategory restaurantCategory) {
        this.restaurantCategories.add(restaurantCategory);
        restaurantCategory.setCategory(this);
    }
}
