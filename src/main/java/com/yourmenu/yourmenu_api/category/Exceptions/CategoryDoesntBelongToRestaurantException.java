package com.yourmenu.yourmenu_api.category.Exceptions;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class CategoryDoesntBelongToRestaurantException extends ApplicationException {
    public CategoryDoesntBelongToRestaurantException(String field, String message) {
        super(field, message);
    }
    public CategoryDoesntBelongToRestaurantException(String field) {
        super(field, "categoira nao pertence ao restaurante");
    }
    public CategoryDoesntBelongToRestaurantException() {
        super("categoryId", "categoira nao pertence ao restaurante");
    }

}
