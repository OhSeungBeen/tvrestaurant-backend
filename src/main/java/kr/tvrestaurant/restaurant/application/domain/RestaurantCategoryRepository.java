package kr.tvrestaurant.restaurant.application.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {

}
