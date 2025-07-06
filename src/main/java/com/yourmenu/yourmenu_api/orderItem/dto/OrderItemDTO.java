package com.yourmenu.yourmenu_api.orderItem.dto;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long id,
        Long dishSizeOptionId,
        String dishName,
        SizeOptionDTO sizeOption,
        Integer quantity,
        BigDecimal price
) {
}
