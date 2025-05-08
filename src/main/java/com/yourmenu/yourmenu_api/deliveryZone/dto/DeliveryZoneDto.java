package com.yourmenu.yourmenu_api.deliveryZone.dto;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;

import java.math.BigDecimal;

public record DeliveryZoneDto(
        Long id,
        String zone,
        BigDecimal deliveryFee,
        Restaurant restaurant
) {
}
