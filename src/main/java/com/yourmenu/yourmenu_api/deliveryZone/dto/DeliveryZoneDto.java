package com.yourmenu.yourmenu_api.deliveryZone.dto;

import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;

import java.math.BigDecimal;

public record DeliveryZoneDto(
        Long id,
        String zone,
        BigDecimal deliveryFee,
        RestaurantDTO restaurant
) {
}
