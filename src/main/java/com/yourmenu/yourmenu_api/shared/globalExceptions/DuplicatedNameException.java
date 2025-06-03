package com.yourmenu.yourmenu_api.shared.globalExceptions;

public class DuplicatedNameException extends ApplicationException {

    public DuplicatedNameException(String field, String message) {
        super(field, message);
    }

    public DuplicatedNameException(String field) {
        super(field, "ja existe um recurso cadastrado com esse nome");
    }
}
