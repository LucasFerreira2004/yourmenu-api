package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto;

public record DishSaveDTO(
        String name,
        String description,
        Boolean isAvailable,
        String imgUrl
) {
}
