package com.yourmenu.yourmenu_api.shared.globalExceptions;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String field) {
        super(field, "recurso nao encontrado");
    }
    public ResourceNotFoundException(String field, String message) {
        super(field, message);
    }

}
