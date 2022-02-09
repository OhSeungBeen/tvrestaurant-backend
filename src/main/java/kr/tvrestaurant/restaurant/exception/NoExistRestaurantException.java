package kr.tvrestaurant.restaurant.exception;

public class NoExistRestaurantException extends RuntimeException{

    public NoExistRestaurantException(String msg) {
        super(msg);
    }
}
