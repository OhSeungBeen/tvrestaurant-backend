package kr.tvrestaurant.restaurant.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import kr.tvrestaurant.restaurant.domain.Category;
import kr.tvrestaurant.restaurant.domain.Restaurant;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class RestaurantCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setCategory (Category category) {
        this.category = category;
    }

    public void setRestaurant (Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
