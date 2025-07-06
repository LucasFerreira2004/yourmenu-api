package com.yourmenu.yourmenu_api.orderItem.dto;

import jakarta.validation.constraints.NotNull;

public record OrderItemSaveDTO(
        @NotNull
        Long dishSizeOptionId,
        @NotNull
        Integer quantity
) {
}
