package com.yourmenu.yourmenu_api.shared.globalExceptions;

public class UserNotAuthenticatedException extends ApplicationException {
    public UserNotAuthenticatedException(String field, String message) {
        super(field, message);
    }

    public UserNotAuthenticatedException(String field) {
        super(field, "Usuário não eutenticado");
    }
}
