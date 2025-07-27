package com.yourmenu.yourmenu_api.order.exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class RestaurantNotOperationException extends ApplicationException {

    public RestaurantNotOperationException(String field, String message) {
      super(field, message);
    }
}
