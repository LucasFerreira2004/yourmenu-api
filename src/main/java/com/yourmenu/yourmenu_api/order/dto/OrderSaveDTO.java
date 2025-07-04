package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemSaveDTO;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OrderSaveDTO(
        @NotNull
        LocalDateTime dateTime,

        @NotNull
        OrderStatus status,

        @NotNull
        String restaurantId,

        @NotNull
        List<OrderItemSaveDTO> orderItems
) {
}
