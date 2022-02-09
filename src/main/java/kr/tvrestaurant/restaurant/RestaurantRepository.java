package kr.tvrestaurant.restaurant;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import kr.tvrestaurant.restaurant.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager em;

    public void save(Restaurant restaurant) {
        em.persist(restaurant);
    }

    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r", Restaurant.class)
            .getResultList();
    }

    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(em.find(Restaurant.class, id));
    }
}
