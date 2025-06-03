package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;

import java.math.BigDecimal;
import java.util.Map;

public record DishSaveDTO(
        String name,
        String description,
        Boolean isAvailable,
        String imgUrl,
        Map<SizeOption, BigDecimal> sizesAndPrices
) {
}
