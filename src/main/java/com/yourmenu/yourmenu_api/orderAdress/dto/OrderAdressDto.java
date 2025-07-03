package com.yourmenu.yourmenu_api.orderAdress.dto;

import com.yourmenu.yourmenu_api.deliveryZone.DeliveryZone;
import com.yourmenu.yourmenu_api.order.Order;

public record OrderAdressDto(
        Order order,
        DeliveryZone deliveryZone,
        Long cep,
        String street,
        String number,
        String complement,
        String reference
) {
}
