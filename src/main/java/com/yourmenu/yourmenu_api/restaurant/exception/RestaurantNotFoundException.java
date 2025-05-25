package com.yourmenu.yourmenu_api.restaurant.exception;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class RestaurantNotFoundException extends ApplicationException {
    public RestaurantNotFoundException(String field, String message) {
        super(field, message);
    }
    public RestaurantNotFoundException(String field) {
        super(field, "Restaurante não encontrado");
    }
    public RestaurantNotFoundException() {
        super("restaurantId", "Restaurante não encontrado");
    }
}
