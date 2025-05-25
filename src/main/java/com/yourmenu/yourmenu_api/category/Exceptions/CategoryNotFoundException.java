package com.yourmenu.yourmenu_api.category.Exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class CategoryNotFoundException extends ApplicationException {
    public CategoryNotFoundException(String field, String message) {
        super(field, message);
    }
    public CategoryNotFoundException(String message) {super("categoryId", message);}
    public CategoryNotFoundException() {super("categoryId", "categoria nao encontrada");}
}
