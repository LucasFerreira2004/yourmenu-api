package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderSaveDTO(
        @NotNull
        LocalDate date,

        @NotNull
        BigDecimal price,

        @NotNull
        OrderStatus status
) {
}
