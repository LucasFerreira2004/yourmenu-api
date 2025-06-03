package com.yourmenu.yourmenu_api.shared.globalExceptions;

public class ResourceWithSameNameException extends ApplicationException {

    public ResourceWithSameNameException(String field, String message) {
        super(field, message);
    }

    public ResourceWithSameNameException(String field) {
        super(field, "ja existe um(a) " + field + " com o mesmo nome ja esta cadastrado no sitema");
    }
}
