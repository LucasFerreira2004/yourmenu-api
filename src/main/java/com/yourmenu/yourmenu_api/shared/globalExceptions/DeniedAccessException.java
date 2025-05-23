package com.yourmenu.yourmenu_api.shared.globalExceptions;

public class DeniedAccessException extends ApplicationException {
    public DeniedAccessException(String field, String message) {
        super(field, message);
    }
    public DeniedAccessException(String field) {
        super(field, "Acesso negado");
    }
}
