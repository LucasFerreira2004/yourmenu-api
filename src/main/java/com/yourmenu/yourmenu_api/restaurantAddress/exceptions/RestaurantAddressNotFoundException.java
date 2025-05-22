package com.yourmenu.yourmenu_api.restaurantAddress.exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class RestaurantAddressNotFoundException extends ApplicationException {
    private static final String message = "endereco do restaurante nao encontrado";
    private static final String field = "restaurantAdress";

    public RestaurantAddressNotFoundException(String field, String message) {
        super(field, message);
    }
    public RestaurantAddressNotFoundException(String field) {
        super(field, message);
    }
    public RestaurantAddressNotFoundException() {
        super(field, message);
    }

}
