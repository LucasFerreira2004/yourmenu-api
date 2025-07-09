package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class SizeOptionNotFound extends ApplicationException {
    public SizeOptionNotFound(String field, String message) {
      super(field, message);
    }
}
