package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderDTO(
    Long id,
    String restaurantId,
    LocalDate date,
    BigDecimal price,
    OrderStatus status
) {
}
