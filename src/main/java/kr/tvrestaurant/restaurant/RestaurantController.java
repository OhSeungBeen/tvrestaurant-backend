package kr.tvrestaurant.restaurant;

import javax.validation.Valid;
import kr.tvrestaurant.restaurant.dto.RestaurantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/api/v1/restaurants")
    public ResponseEntity<?> addRestaurant(@RequestBody @Valid RestaurantRequestDto restaurantRequestDto,
        BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(restaurantService.addRestaurant(restaurantRequestDto));
    }

    @GetMapping("/api/v1/restaurants")
    public ResponseEntity<?> getRestaurantList() {
        return ResponseEntity.ok().body(restaurantService.getRestaurantList());
    }

    @GetMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<?> getRestaurantDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(restaurantService.getRestaurantDetails(id));
    }

    @PutMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<?> updateRestaurants(@PathVariable(name = "id") Long id
        , @RequestBody @Valid RestaurantRequestDto restaurantRequestDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return  ResponseEntity.ok().body(restaurantService.updateRestaurant(id, restaurantRequestDto));
    }

}
