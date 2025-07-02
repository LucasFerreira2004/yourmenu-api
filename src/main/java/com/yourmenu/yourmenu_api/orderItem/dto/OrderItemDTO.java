package com.yourmenu.yourmenu_api.orderItem.dto;

import jakarta.persistence.Id;

public record OrderItemDTO(
        Long id,
        Long orderItem
) {
}
