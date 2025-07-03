package com.yourmenu.yourmenu_api.orderItem.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long id,
        Long dishSizeOptionId,
        Integer quantity,
        String dishName,
        BigDecimal price
) {
}
