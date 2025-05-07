package com.yourmenu.yourmenu_api.restaurant.exception;

import com.yourmenu.yourmenu_api.globalExceptions.ApplicationException;

public class RestaurantNotFound extends ApplicationException {
    public RestaurantNotFound(String field, String message) {
        super(field, message);
    }
    public RestaurantNotFound(String field) {
        super(field, "Restaurante não encontrado");
    }
}
