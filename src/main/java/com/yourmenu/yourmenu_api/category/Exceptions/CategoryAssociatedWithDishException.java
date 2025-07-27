package com.yourmenu.yourmenu_api.category.Exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class CategoryAssociatedWithDishException extends ApplicationException {

    public CategoryAssociatedWithDishException() {
        super("CategoryId", "Categoria ainda associada a um prato");
    }
}
