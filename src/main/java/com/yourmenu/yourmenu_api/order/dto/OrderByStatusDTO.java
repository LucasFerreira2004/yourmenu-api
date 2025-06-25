package com.yourmenu.yourmenu_api.order.dto;

import com.yourmenu.yourmenu_api.order.OrderStatus;

import java.util.List;

public record OrderByStatusDTO(
        OrderStatus status,
        List<OrderDTO> orders
) {
}
