package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressDto;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemDTO;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
    Long id,
    String restaurantId,
    LocalDateTime dateTime,
    BigDecimal price,
    OrderStatus status,
    String note,
    List<OrderItemDTO> orderItems,
    OrderAdressDto orderAdress,
    OrderClientDTO orderClientDTO
) {
}
