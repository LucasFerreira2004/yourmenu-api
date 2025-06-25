package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.MeasureUnit;

public record SizeOptionDTO(
        Long id,
        String magnitude,
        MeasureUnit measureUnit
) {
}
