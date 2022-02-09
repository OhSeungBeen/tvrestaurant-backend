package kr.tvrestaurant.restaurant;

import java.util.List;
import javax.persistence.EntityManager;
import kr.tvrestaurant.restaurant.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final EntityManager em;

    public void save(Menu menu) {
        em.persist(menu);
    }

    public Menu findOne(Long id) {
        return em.find(Menu.class, id);
    }

    public List<Menu> findAll() {
        return em.createQuery("select r from menu r", Menu.class)
            .getResultList();
    }
}
