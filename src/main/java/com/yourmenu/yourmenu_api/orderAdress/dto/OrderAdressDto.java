package com.yourmenu.yourmenu_api.orderAdress.dto;

import com.yourmenu.yourmenu_api.deliveryZone.DeliveryZone;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZoneDto;
import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.dto.OrderDTO;

public record OrderAdressDto(
        Long id,
        DeliveryZoneDto deliveryZone,
        Long cep,
        String street,
        String number,
        String complement,
        String reference
) {
}
