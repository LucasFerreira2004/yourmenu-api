package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record OrderDTO(
    Long id,
    String restaurantId,
    LocalDateTime dateTime,
    BigDecimal price,
    OrderStatus status
) {
}
