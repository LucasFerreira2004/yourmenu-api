package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;

import java.util.List;

public record DishDTO(
        Long id,
        String restaurantId,
        Long categoryId,
        String name,
        String description,
        Boolean isAvailable,
        String imgUrl,
        List<SizeOptionPriceDTO>sizeOptionsPrices
) {
}
