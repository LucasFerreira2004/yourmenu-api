package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderSaveDTO(
        @NotNull
        LocalDateTime dateTime,

        @NotNull
        BigDecimal price,

        @NotNull
        OrderStatus status
) {
}
