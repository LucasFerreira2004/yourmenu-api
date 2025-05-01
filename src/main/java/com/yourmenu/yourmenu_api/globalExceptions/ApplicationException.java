package com.yourmenu.yourmenu_api.globalExceptions;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {
    private final String field;

    protected ApplicationException(String field, String message) {
        super(message);
        this.field = field;
    }
}
