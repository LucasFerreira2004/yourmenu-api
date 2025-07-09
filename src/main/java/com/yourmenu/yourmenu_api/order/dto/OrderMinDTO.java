package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressDto;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.List;

public record OrderMinDTO(
        Long orderId,
        OrderStatus status,
        List<OrderItemDTO> orderItems,
        OrderAdressDto orderAdress,
        BigDecimal price
) {
}
