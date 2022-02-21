package kr.tvrestaurant.restaurant.application.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import org.aspectj.weaver.loadtime.definition.Definition;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String tel;

    @Column(columnDefinition = "POINT")
    private Point location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Type> types = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantCategory> restaurantCategories = new ArrayList<>();

    public void setMenus(List<Menu> menus) {
        menus.forEach(menu -> menu.setRestaurant(this));
        this.menus = menus;
    }

    public void setTypes(List<Type> types) {
        types.forEach(type -> type.setRestaurant(this));
        this.types = types;
    }

    public void setRestaurantCategories(List<RestaurantCategory> restaurantCategories) {
        restaurantCategories.forEach(restaurantCategory -> restaurantCategory.setRestaurant(this));
        this.restaurantCategories = restaurantCategories;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
        menu.setRestaurant(this);
    }

    public void addType(Type type) {
        this.types.add(type);
        type.setRestaurant(this);
    }

    public void addRestaurantCategory(RestaurantCategory restaurantCategory) {
        this.restaurantCategories.add(restaurantCategory);
        restaurantCategory.setRestaurant(this);
    }
}
