package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto;

import java.math.BigDecimal;

public record SizeOptionPriceDTO(
        Long dishSizeOptionId,
        Long sizeOptionId,
        String magnitude,
        String measureUnit,
        BigDecimal price
) {}
