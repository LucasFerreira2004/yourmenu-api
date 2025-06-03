package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;

import java.math.BigDecimal;
import java.util.Map;

public record DishDTO(
        Long id,
        String restaurantId,
        Long categoryId,
        String name,
        String description,
        Boolean isAvailable,
        String imgUrl
        Map<SizeOptionDTO, BigDecimal>
) {
}
