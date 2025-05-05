package com.yourmenu.yourmenu_api.globalExceptions;

public class UserNotAuthenticated extends ApplicationException {
    public UserNotAuthenticated(String field, String message) {
        super(field, message);
    }

    public UserNotAuthenticated(String field) {
        super(field, "Usuário não eutenticado");
    }
}
