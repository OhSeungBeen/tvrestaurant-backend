package kr.tvrestaurant.util;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.tvrestaurant.restaurant.application.domain.Category;
import kr.tvrestaurant.restaurant.application.domain.CategoryRepository;
import kr.tvrestaurant.restaurant.application.domain.Restaurant;
import kr.tvrestaurant.restaurant.application.domain.RestaurantCategory;
import kr.tvrestaurant.restaurant.application.domain.RestaurantRepository;
import kr.tvrestaurant.restaurant.application.domain.Type;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
@RequiredArgsConstructor
public class DataProvider implements ApplicationRunner {
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    ClassPathResource resource = new ClassPathResource("data/restaurant.csv");

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(resource.getFile()));

        csvReader.readNext();
        Iterator<String[]> iterator = csvReader.iterator();

        while(iterator.hasNext()) {
            String[] data = iterator.next();
            String[] location = data[column.LOCATION.ordinal()].split(",");
            String[] StringArrayTypes = data[column.TYPE.ordinal()].split(",");
            String[] categoryNames = data[column.CATEGORY.ordinal()].split(",");

            List<Type> types = new ArrayList<>();
            for(int i = 0; i < StringArrayTypes.length; i = i + 2) {
                Type type = Type.builder()
                    .name(StringArrayTypes[i])
                    .episode(Integer.parseInt(StringArrayTypes[i + 1]))
                    .build();
                types.add(type);
            }

            List<RestaurantCategory> restaurantCategories = new ArrayList<>();
            for(String categoryName: categoryNames) {
                RestaurantCategory restaurantCategory = new RestaurantCategory();
                Category category = categoryRepository.findByName(categoryName);
                category.addRestaurantCategory(restaurantCategory);

                restaurantCategories.add(restaurantCategory);
            }

            Restaurant restaurant = Restaurant.builder()
                .name(data[column.NAME.ordinal()])
                .tel(data[column.TEL.ordinal()])
                .address(data[column.ADDRESS.ordinal()])
//                .location(new Point(Double.parseDouble(location[0]), Double.parseDouble(location[1])))
                .types(types)
                .build();
            restaurant.setTypes(types);
            restaurant.setRestaurantCategories(restaurantCategories);
            restaurantRepository.save(restaurant);
        }
    }

    enum column {
        TYPE,NAME,TEL,ADDRESS,LOCATION,CATEGORY
    }
}
