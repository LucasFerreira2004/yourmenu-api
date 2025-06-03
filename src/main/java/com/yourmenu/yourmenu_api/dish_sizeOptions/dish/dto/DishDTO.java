package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto;

public record DishDTO(
        Long id,
        String restaurantId,
        Long categoryId,
        String name,
        String description,
        Boolean isAvailable,
        String imgUrl
) {
}
