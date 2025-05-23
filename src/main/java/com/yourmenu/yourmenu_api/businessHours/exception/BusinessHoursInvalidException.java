package com.yourmenu.yourmenu_api.businessHours.exception;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class BusinessHoursInvalidException extends ApplicationException {
    public BusinessHoursInvalidException(String field, String message) {
        super(field, message);
    }

    public BusinessHoursInvalidException(String message) {
        super("businessHours", message);
    }
}
