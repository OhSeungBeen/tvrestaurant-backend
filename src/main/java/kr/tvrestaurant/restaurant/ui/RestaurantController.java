package kr.tvrestaurant.restaurant.ui;

import javax.validation.Valid;
import kr.tvrestaurant.restaurant.application.RestaurantService;
import kr.tvrestaurant.restaurant.dto.RestaurantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    // 레스토랑 추가
    @PostMapping("/api/v1/restaurants")
    public ResponseEntity<?> addRestaurant(@RequestBody @Valid RestaurantRequestDto restaurantRequestDto,
        BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(restaurantService.addRestaurant(restaurantRequestDto));
    }

    // 레스토랑 전체 조회
    @GetMapping("/api/v1/restaurants")
    public ResponseEntity<?> getRestaurantList() {
        return ResponseEntity.ok().body(restaurantService.getRestaurantList());
    }

    // 레스토랑 조회
    @GetMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<?> getRestaurantDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(restaurantService.getRestaurantDetails(id));
    }

    // 레스토랑 전체 수정
    @PutMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<?> updateRestaurants(@PathVariable("id") Long id
        , @RequestBody @Valid RestaurantRequestDto restaurantRequestDto, BindingResult bindingResult) throws IllegalArgumentException{

        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return  ResponseEntity.ok().body(restaurantService.updateRestaurant(id, restaurantRequestDto));
    }

    // 레스토랑 삭제
    @DeleteMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<?> removeRestaurant(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(restaurantService.removeRestaurant(id));
    }

}
