package com.yourmenu.yourmenu_api.restaurantAdress.exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class RestaurantAdressNotFoundException extends ApplicationException {
    private static final String message = "endereco do restaurante nao encontrado";
    private static final String field = "restaurantAdress";

    public RestaurantAdressNotFoundException(String field, String message) {
        super(field, message);
    }
    public RestaurantAdressNotFoundException(String field) {
        super(field, message);
    }
    public RestaurantAdressNotFoundException() {
        super(field, message);
    }

}
