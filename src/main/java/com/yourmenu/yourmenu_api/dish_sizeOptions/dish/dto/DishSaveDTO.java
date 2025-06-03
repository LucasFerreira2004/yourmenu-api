package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.shared.notations.nullOrNotBlank.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record DishSaveDTO(
        @NotBlank
        String name,

        @NullOrNotBlank
        String description,

        Boolean isAvailable,
        String imgUrl,
        List<SizeOptionPriceDTO> sizeOptionsPrices
) {
}
